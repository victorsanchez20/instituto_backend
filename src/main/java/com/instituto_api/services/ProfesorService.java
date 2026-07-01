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

    public Profesores update(Long id, Profesores updated) {
        Profesores profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));
        profesor.setNombre(updated.getNombre());
        profesor.setApellido(updated.getApellido());
        profesor.setDocidentidad(updated.getDocidentidad());
        profesor.setTelefono(updated.getTelefono());
        profesor.setEspecialidad(updated.getEspecialidad());
        profesor.setEmail(updated.getEmail());
        profesor.setUsuario(updated.getUsuario());
        if (updated.getPassword() != null && !updated.getPassword().isEmpty()) {
            profesor.setPassword(updated.getPassword());
        }
        return profesorRepository.save(profesor);
    }

    public void deleteById(Long id) {
        this.profesorRepository.deleteById(id);
    }

    public long totalProfesores() {
        return this.profesorRepository.totalProfesores();
    }
}
