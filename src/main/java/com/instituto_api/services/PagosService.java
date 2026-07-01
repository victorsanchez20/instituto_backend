package com.instituto_api.services;

import com.instituto_api.models.Aula;
import com.instituto_api.models.Curso;
import com.instituto_api.models.Inscripcion;
import com.instituto_api.models.InscripcionRequest;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagosService {

    private final CursoService cursoService;
    private final InscripcionService inscripcionService;
    private final EstadoInscripcionService estadoInscripcionService;
    private final AlumnoService alumnoService;
    private final AulaService aulaService;

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @Value("${app.base-url-prod:https://instituto-uci.vercel.app}")
    private String baseUrlProd;

    @Value("${app.api-url-prod:https://instituto-backend-j0cf.onrender.com}")
    private String apiUrlProd;

    public PagosService(CursoService cursoService,
                        InscripcionService inscripcionService,
                        EstadoInscripcionService estadoInscripcionService,
                        AlumnoService alumnoService,
                        AulaService aulaService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
        this.estadoInscripcionService = estadoInscripcionService;
        this.alumnoService = alumnoService;
        this.aulaService = aulaService;
    }

    public String crearPago(Long inscripcionId, String frontendUrl) throws Exception {
        Inscripcion inscripcion = inscripcionService.getInscriptionById(inscripcionId);
        Curso curso = inscripcion.getAula().getCurso();

        PreferenceItemRequest item =
            PreferenceItemRequest.builder()
                .title(curso.getNombre())
                .quantity(1)
                .currencyId("PEN")
                .unitPrice(curso.getPrecio())
                .build();

        String baseUrl = (frontendUrl != null && !frontendUrl.isEmpty())
                ? frontendUrl.replaceAll("/$", "")
                : baseUrlProd;

        String successUrl = baseUrl + "/pago/exito";
        String failureUrl = baseUrl + "/pago/error";
        String pendingUrl = baseUrl + "/pago/pendiente";

        System.out.println("Creando preferencia - baseUrl: " + baseUrl
                + " | success: " + successUrl
                + " | profile: " + activeProfile);

        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success(successUrl)
                        .failure(failureUrl)
                        .pending(pendingUrl)
                        .build();

        PreferenceRequest.PreferenceRequestBuilder builder = PreferenceRequest.builder()
                .items(List.of(item))
                .backUrls(backUrls)
                .externalReference(inscripcionId.toString());

        if (!"local".equals(activeProfile)) {
            builder.autoReturn("approved");
            builder.notificationUrl(apiUrlProd + "/api/pagos/webhook");
        }

        PreferenceRequest preferenceRequest = builder.build();
        PreferenceClient client = new PreferenceClient();

        try {
            Preference preference = client.create(preferenceRequest);
            return preference.getInitPoint();
        } catch (MPApiException e) {
            System.out.println("STATUS: " + e.getStatusCode());
            System.out.println("RESPONSE: " + e.getApiResponse().getContent());
            throw new RuntimeException("Error al crear preferencia de pago: " + e.getApiResponse().getContent());
        }
    }

    public String crearPagoDesdeCurso(Long cursoId, Long aulaId, Long alumnoId, String frontendUrl) throws Exception {
        if (alumnoId == null) {
            throw new RuntimeException("Se requiere alumnoId para crear inscripción desde curso");
        }
        if (aulaId == null) {
            List<Aula> aulas = aulaService.obtenerAulasPorCurso(cursoId);
            if (aulas.isEmpty()) {
                throw new RuntimeException("No hay aulas disponibles para el curso " + cursoId);
            }
            aulaId = aulas.get(0).getId();
        }
        InscripcionRequest request = new InscripcionRequest(alumnoId, aulaId, null);
        Inscripcion inscripcion = inscripcionService.create(request);
        return crearPago(inscripcion.getIdInscripcion(), frontendUrl);
    }

    public String verificarPago(String paymentId) {
        try {
            PaymentClient client = new PaymentClient();
            Payment payment = client.get(Long.valueOf(paymentId));

            String status = payment.getStatus();
            String externalReference = payment.getExternalReference();

            System.out.println("Pago verificado - ID: " + paymentId
                    + ", Status: " + status
                    + ", ExternalRef: " + externalReference);

            if ("approved".equals(status) && externalReference != null) {
                Long inscripcionId = Long.valueOf(externalReference);
                Long estadoAprobadoId = (long) estadoInscripcionService.findByCodigo("APR").getId();
                inscripcionService.cambiarEstado(inscripcionId, estadoAprobadoId);
                return "APROBADO";
            }

            return status;
        } catch (Exception e) {
            System.out.println("Error al verificar pago: " + e.getMessage());
            return "ERROR: " + e.getMessage();
        }
    }

    public String procesarWebhook(String action, String dataId) {
        if (action != null && action.startsWith("payment") && dataId != null) {
            return verificarPago(dataId);
        }
        return "IGNORADO";
    }
}
