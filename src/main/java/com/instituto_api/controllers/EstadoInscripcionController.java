package com.instituto_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto_api.models.EstadoInscripcion;
import com.instituto_api.services.EstadoInscripcionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/instituto/estado")
public class EstadoInscripcionController {

    private final EstadoInscripcionService estadoInscripcionService;

    public EstadoInscripcionController(EstadoInscripcionService estadoInscripcionService) {
        this.estadoInscripcionService = estadoInscripcionService;
    }

    @GetMapping()
    public List<EstadoInscripcion> getAllEstados() {
        return this.estadoInscripcionService.getAllEstados();
    }
    
}
