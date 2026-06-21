package com.instituto_api.controllers;

import com.instituto_api.services.PagosService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    private final PagosService pagoService;

    public PagosController(PagosService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping("/crear")
    public Map<String, String> crearPago() throws Exception {

        String url = pagoService.crearPago();

        return Map.of("url", url);
    }
}