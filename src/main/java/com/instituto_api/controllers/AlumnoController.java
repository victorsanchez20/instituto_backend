package com.instituto_api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAlumno(@PathVariable Long id) {
        Optional<Alumno> alumnoOpt = alumnoService.getById(id);
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            alumno.setPassword(null);
            return ResponseEntity.ok(alumno);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
    }

    @PostMapping
    public Alumno crearAlumno(@RequestBody Alumno alumno) {
        return this.alumnoService.saveAlumno(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        try {
            Alumno actualizado = alumnoService.updateAlumno(id, alumno);
            actualizado.setPassword(null);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> cambiarPassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String oldPassword = body.get("oldPassword");
        String newPassword = body.get("newPassword");
        if (oldPassword == null || newPassword == null || newPassword.length() < 6) {
            return ResponseEntity.badRequest().body("Contraseña nueva debe tener al menos 6 caracteres");
        }
        boolean success = alumnoService.changePassword(id, oldPassword, newPassword);
        if (success) {
            return ResponseEntity.ok().body("Contraseña actualizada correctamente");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña actual incorrecta");
    }

    @GetMapping("/total")
    public Number totalEstudiantes() {
        return this.alumnoService.totalAlumnos();
    }
}
