package com.instituto_api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.instituto_api.models.Anuncio;
import com.instituto_api.repositories.AnuncioRepository;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    public List<Anuncio> getAll() {
        return anuncioRepository.findAll();
    }

    public List<Anuncio> getActivos() {
        return anuncioRepository.findByActivoTrueOrderByFechaCreacionDesc();
    }

    public Anuncio save(Anuncio anuncio) {
        if (anuncio.getFechaCreacion() == null) {
            anuncio.setFechaCreacion(LocalDateTime.now());
        }
        return anuncioRepository.save(anuncio);
    }

    public Anuncio update(Long id, Anuncio anuncio) {
        Anuncio existente = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio no encontrado"));
        existente.setTitulo(anuncio.getTitulo());
        existente.setDescripcion(anuncio.getDescripcion());
        existente.setImagenUrl(anuncio.getImagenUrl());
        existente.setActivo(anuncio.isActivo());
        return anuncioRepository.save(existente);
    }

    public void delete(Long id) {
        anuncioRepository.deleteById(id);
    }
}
