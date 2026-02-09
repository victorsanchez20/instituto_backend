package com.instituto_api.services;

import java.util.List;

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
}