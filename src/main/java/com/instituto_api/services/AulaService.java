package com.instituto_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.instituto_api.models.Aula;
import com.instituto_api.repositories.AulaRepository;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public List<Aula> getAllAulas() {
        return this.aulaRepository.findAll();
    }

    public Aula save(Aula aula) {
        return this.aulaRepository.save(aula);
    }   

    public Aula update(Aula aula, Long id) {
        aula.setId(id);
        return this.aulaRepository.save(aula);
    }

    public void deleteById(long id) {
        this.aulaRepository.deleteById(id);
    }
}
