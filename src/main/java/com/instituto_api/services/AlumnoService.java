package com.instituto_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.instituto_api.models.Alumno;
import com.instituto_api.repositories.AlumnoRepository;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    } 

    public Alumno saveAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void deleteById(long id) {
        this.alumnoRepository.deleteById(id);
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
                .filter(alumno -> alumno.getPassword().equals(password)); 
                // El filter verifica si la contraseña coincide. 
                // Si coincide, devuelve el Alumno. Si no, devuelve vacío.
    }

    // ... aquí siguen tus otros métodos como getAllAlumnos() y saveAlumno()
}