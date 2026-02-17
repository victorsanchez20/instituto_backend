package com.instituto_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.EstadoInscripcion;

@Repository
public interface EstadoInscripcionRepository extends JpaRepository<EstadoInscripcion, Long>{
    Optional<EstadoInscripcion> findByCodigo(String codigo);
}
