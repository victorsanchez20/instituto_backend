package com.instituto_api.services;

import com.instituto_api.models.Curso;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.preference.Preference;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagosService {

    private final CursoService cursoService;

    public PagosService(CursoService cursoService) {
        this.cursoService = cursoService;
    }

        public String crearPago(Long cursoId) throws Exception {
        Curso curso = cursoService.getById(cursoId).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        PreferenceItemRequest item =
            PreferenceItemRequest.builder()
                .title(curso.getNombre())
                .quantity(1)
                .currencyId("PEN")
                .unitPrice(curso.getPrecio())
                .build();

        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("http://localhost:4200/pago/exito")
                        .failure("http://localhost:4200/pago/error")
                        .pending("http://localhost:4200/pago/pendiente")
                        .build();

        PreferenceRequest preferenceRequest =
                PreferenceRequest.builder()
                        .items(List.of(item))
                        .backUrls(backUrls)
                        .build();

        
        PreferenceClient client = new PreferenceClient();

    
        try {
            Preference preference = client.create(preferenceRequest);
            return preference.getInitPoint();

        } catch (MPApiException e) {

            System.out.println("STATUS: " + e.getStatusCode());
            System.out.println("RESPONSE: " + e.getApiResponse().getContent());

            return e.getApiResponse().getContent();
        }
    }
}