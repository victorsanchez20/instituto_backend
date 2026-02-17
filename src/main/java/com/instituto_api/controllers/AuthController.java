package com.instituto_api.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto_api.models.Alumno;
import com.instituto_api.services.AlumnoService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    AlumnoService alumnoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        System.out.println("Usuario recibido: " + credenciales.get("user"));
        System.out.println("Password recibida: " + credenciales.get("pass"));


        // Cambiamos el tipo a Optional<Alumno>
        Optional<Alumno> alumnoOpt = alumnoService.login(credenciales.get("user"), credenciales.get("pass"));

        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get(); // Aquí extraemos al alumno de la "caja"
            alumno.setPassword(null);
            return ResponseEntity.ok(alumno);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

}
