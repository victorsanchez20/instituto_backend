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

import com.instituto_api.models.Admin;
import com.instituto_api.models.Alumno;
import com.instituto_api.services.AdminService;
import com.instituto_api.services.AlumnoService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        System.out.println("Usuario recibido: " + credenciales.get("user"));
        System.out.println("Password recibida: " + credenciales.get("pass"));

        Optional<Alumno> alumnoOpt = alumnoService.login(credenciales.get("user"), credenciales.get("pass"));

        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            alumno.setPassword(null);
            return ResponseEntity.ok(alumno);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> credenciales) {
        System.out.println("Admin login - Usuario: " + credenciales.get("user"));

        Optional<Admin> adminOpt = adminService.login(credenciales.get("user"), credenciales.get("pass"));

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            admin.setPassword(null);
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales de administrador inválidas");
        }
    }
}
