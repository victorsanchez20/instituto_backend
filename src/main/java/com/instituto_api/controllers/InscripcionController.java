package com.instituto_api.controllers;

import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.instituto_api.dto.ActualizarEstadoRequest;
import com.instituto_api.dto.InscripcionPorAulaDTO;
import com.instituto_api.models.Aula;
import com.instituto_api.models.Inscripcion;
import com.instituto_api.models.InscripcionRequest;
import com.instituto_api.services.AulaService;
import com.instituto_api.services.InscripcionService;

@RestController
@RequestMapping("api/instituto/inscripcion")
public class InscripcionController {

    private final InscripcionService inscripcionService;
    private final AulaService aulaService;

    public InscripcionController(InscripcionService inscripcionService,
                                AulaService aulaService
    ) {
        this.inscripcionService = inscripcionService;
        this.aulaService = aulaService; 
    }

    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> getAll() {
        return inscripcionService.getAllInscripciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> getById(@PathVariable Long id) {
        Inscripcion inscripcion = inscripcionService.getInscriptionById(id);
        return ResponseEntity.ok(inscripcion);
    }
    

    // OBTENER INSCRIPCIONES POR ID DE ALUMNO
    // Ejemplo: GET /api/instituto/inscripcion/alumno/5
    @GetMapping("/alumno/{alumnoId}")
    public List<Inscripcion> getByAlumno(@PathVariable Long alumnoId) {
        return inscripcionService.getInscripcionByAlumno(alumnoId);
    }

    // Crear una nueva inscripción
    @PostMapping
    public Inscripcion save(@RequestBody InscripcionRequest inscripcion) {
        return inscripcionService.create(inscripcion);
    }

    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable Long id) {
        inscripcionService.cancelar(id);
    }

    @GetMapping("/cantidad-por-aula")
    public List<Map<String, Object>> cantidadPorAula() {
        return inscripcionService.obtenerCantidadPorAula();
    }

    @GetMapping("/alumno/{id}/clases-hoy")
    public List<Aula> clasesHoy(@PathVariable Long id) {
        return aulaService.aulasActivasHoy(id);
    }

    @GetMapping("/aula/{aulaId}")
    public List<InscripcionPorAulaDTO> obtenerPorAula(@PathVariable Long aulaId) {
        return inscripcionService.obtenerPorAula(aulaId);
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Long id,
            @RequestBody ActualizarEstadoRequest request) {

        inscripcionService.cambiarEstado(id, request.getEstadoId());
        return ResponseEntity.ok().build();
    }


}
