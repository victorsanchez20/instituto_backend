package com.instituto_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.instituto_api.models.EstadoInscripcion;
import com.instituto_api.repositories.EstadoInscripcionRepository;

@Service
public class EstadoInscripcionService {

    private final EstadoInscripcionRepository estadoInscripcionRepository;

    public EstadoInscripcionService(EstadoInscripcionRepository estadoInscripcionRepository) {
        this.estadoInscripcionRepository = estadoInscripcionRepository;
    }

    public List<EstadoInscripcion> getAllEstados() {
        return this.estadoInscripcionRepository.findAll();
    }
}
