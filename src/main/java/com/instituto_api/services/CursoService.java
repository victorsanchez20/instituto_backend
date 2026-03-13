package com.instituto_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.instituto_api.models.Curso;
import com.instituto_api.repositories.CursoRepository;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getAllCursos() {
        return this.cursoRepository.findAll();
    }

    public Curso save(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    public void deleteById(Long id) {
        this.cursoRepository.deleteById(id);
    }

    public Curso update(Long id, Curso curso) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado");
        }

        curso.setId(id);
        return this.cursoRepository.save(curso);
    }

    public Optional<Curso> getById(Long id) {
        return this.cursoRepository.findById(id);
    }

    public Long totalCursos() {
        return this.cursoRepository.totalCursos();
    }

    public List<Object[]> totalInscritosPorCurso() {
        return cursoRepository.totalInscritosPorCurso();
    }
}
