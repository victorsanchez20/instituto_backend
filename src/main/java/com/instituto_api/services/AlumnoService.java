package com.instituto_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.instituto_api.models.Alumno;
import com.instituto_api.repositories.AlumnoRepository;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;

    public AlumnoService(AlumnoRepository alumnoRepository,
                         PasswordEncoder passwordEncoder
    ) {
        this.alumnoRepository = alumnoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    } 

    public Alumno saveAlumno(Alumno alumno) {
        alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));
        return alumnoRepository.save(alumno);
    }

    public Optional<Alumno> getById(Long id) {
        return alumnoRepository.findById(id);
    }

    public void deleteById(long id) {
        this.alumnoRepository.deleteById(id);
    }

    public Alumno updateAlumno(Long id, Alumno updated) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        alumno.setApellidos(updated.getApellidos());
        alumno.setNombres(updated.getNombres());
        alumno.setEmail(updated.getEmail());
        alumno.setUsuario(updated.getUsuario());
        alumno.setDocumento(updated.getDocumento());
        alumno.setTelefono(updated.getTelefono());
        alumno.setFechaNacimiento(updated.getFechaNacimiento());
        alumno.setDireccion(updated.getDireccion());
        alumno.setGenero(updated.getGenero());
        return alumnoRepository.save(alumno);
    }

    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        if (!passwordEncoder.matches(oldPassword, alumno.getPassword())) {
            return false;
        }
        alumno.setPassword(passwordEncoder.encode(newPassword));
        alumnoRepository.save(alumno);
        return true;
    }

    /**
     * Método para validar el acceso de un alumno
     * @param usuario Nombre de usuario enviado desde el front
     * @param password Contraseña enviada desde el front
     * @return El objeto Alumno si es válido, de lo contrario un Optional vacío
     */
    public Optional<Alumno> login(String usuario, String password) {
        // 1. Buscamos al alumno por su nombre de usuario
        return alumnoRepository.findByUsuario(usuario)
                .filter(alumno -> passwordEncoder.matches(password, alumno.getPassword())); 
                // El filter verifica si la contraseña coincide. 
                // Si coincide, devuelve el Alumno. Si no, devuelve vacío.
    }

    // ... aquí siguen tus otros métodos como getAllAlumnos() y saveAlumno()

    public Long totalAlumnos() {
        return alumnoRepository.totalAlumnos();
    }
}