package com.instituto_api.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.instituto_api.models.EstadoInscripcion;
import com.instituto_api.models.Inscripcion;
import com.instituto_api.repositories.EstadoInscripcionRepository;
import com.instituto_api.repositories.InscripcionRepository;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstadoInscripcionRepository estadoRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository,
                            EstadoInscripcionRepository estadoRepository
    ) {
        this.inscripcionRepository = inscripcionRepository;
        this.estadoRepository = estadoRepository;
    }

    public List<Inscripcion> getAllInscripciones() {
        return this.inscripcionRepository.findAll();
    } 

    public Inscripcion create(Inscripcion inscripcion) {

        EstadoInscripcion estadoPendiente = estadoRepository
                .findByCodigo("PEN")
                .orElseThrow(() -> new RuntimeException("Estado PEN no existe en BD"));

        inscripcion.setEstado(estadoPendiente);

        // fecha automática (otra regla común)
        inscripcion.setFechaCreacion(LocalDate.now());

        return inscripcionRepository.save(inscripcion);
    }

    public void deleteById(Long id) {
        this.inscripcionRepository.deleteById(id);
    }

    public List<Inscripcion> getInscripcionByAlumno(Long id) {
        return this.inscripcionRepository.findByAlumno_Id(id);
    }
}
