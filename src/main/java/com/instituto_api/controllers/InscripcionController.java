package com.instituto_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto_api.models.Inscripcion;
import com.instituto_api.services.InscripcionService;

@RestController
@RequestMapping("api/instituto/inscripcion")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> getAll() {
        return inscripcionService.getAllInscripciones();
    }

    // OBTENER INSCRIPCIONES POR ID DE ALUMNO
    // Ejemplo: GET /api/instituto/inscripcion/alumno/5
    @GetMapping("/alumno/{alumnoId}")
    public List<Inscripcion> getByAlumno(@PathVariable Long alumnoId) {
        return inscripcionService.getInscripcionByAlumno(alumnoId);
    }

    // Crear una nueva inscripción
    @PostMapping
    public Inscripcion save(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.create(inscripcion);
    }

    // Eliminar una inscripción
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inscripcionService.deleteById(id);
    }
}
