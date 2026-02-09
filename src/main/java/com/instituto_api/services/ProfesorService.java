package com.instituto_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.instituto_api.models.Profesores;
import com.instituto_api.repositories.ProfesorRepository;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesores> getAllProfesores() {
        return this.profesorRepository.findAll();
    }

    public Profesores save(Profesores profesor) {
        return this.profesorRepository.save(profesor);
    }

    public void deleteById(Long id) {
        this.profesorRepository.deleteById(id);
    }
}
