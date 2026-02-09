package com.instituto_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto_api.models.Alumno;
import com.instituto_api.services.AlumnoService;

@RestController
@RequestMapping("/api/instituto/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> listarAlumnos() {
        return this.alumnoService.getAllAlumnos();
    }

    @PostMapping
    public Alumno crearAlumno(@RequestBody Alumno alumno) {
        return this.alumnoService.saveAlumno(alumno);
    }
}
