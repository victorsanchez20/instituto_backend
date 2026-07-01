package com.instituto_api.controllers;

import com.instituto_api.services.PagosService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    private final PagosService pagoService;

    public PagosController(PagosService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping("/crear")
    public Map<String, String> crearPago(@RequestParam(required = false) Long inscripcionId,
                                          @RequestParam(required = false) Long cursoId,
                                          @RequestParam(required = false) Long aulaId,
                                          @RequestParam(required = false) Long alumnoId,
                                          @RequestParam String frontendUrl) throws Exception {
        if (inscripcionId != null) {
            String url = pagoService.crearPago(inscripcionId, frontendUrl);
            return Map.of("url", url);
        }
        if (cursoId != null) {
            String url = pagoService.crearPagoDesdeCurso(cursoId, aulaId, alumnoId, frontendUrl);
            return Map.of("url", url);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Debe enviar 'inscripcionId' o 'cursoId'");
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarPago(@RequestParam String paymentId) {
        String resultado = pagoService.verificarPago(paymentId);
        if ("APROBADO".equals(resultado)) {
            return ResponseEntity.ok(Map.of("status", "approved", "message", "Pago confirmado e inscripción actualizada"));
        }
        return ResponseEntity.ok(Map.of("status", resultado, "message", "Pago no aprobado aún"));
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> webhook(@RequestBody Map<String, Object> body) {
        String action = body.get("action") != null ? (String) body.get("action")
                    : body.get("type") != null ? (String) body.get("type") : "";
        String dataId = null;
        if (body.get("data") instanceof Map) {
            dataId = (String) ((Map<?, ?>) body.get("data")).get("id");
        }
        pagoService.procesarWebhook(action, dataId);
        return ResponseEntity.ok("OK");
    }
}
