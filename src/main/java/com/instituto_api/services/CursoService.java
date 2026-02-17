package com.instituto_api.services;

import java.util.List;

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
        curso.setId(id);
        return this.cursoRepository.save(curso);
    }

    public Curso getById(Long id) {
        return this.cursoRepository.getById(id);
    }
}
