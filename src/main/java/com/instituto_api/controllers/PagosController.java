package com.instituto_api.controllers;

import com.instituto_api.services.PagosService;

import org.springframework.http.HttpStatus;
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
    public Map<String, String> crearPago(@RequestParam(required = false) Long cursoId,
                                         @RequestBody(required = false) Map<String, Object> body) throws Exception {

        if (cursoId == null) {
            if (body != null && body.get("cursoId") != null) {
                try {
                    cursoId = Long.valueOf(String.valueOf(body.get("cursoId")));
                } catch (NumberFormatException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid cursoId format");
                }
            }
        }

        if (cursoId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter 'cursoId'");
        }

        String url = pagoService.crearPago(cursoId);

        return Map.of("url", url);
    }
}