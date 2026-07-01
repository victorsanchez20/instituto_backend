package com.instituto_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto_api.models.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByActivoTrueOrderByFechaCreacionDesc();
}
