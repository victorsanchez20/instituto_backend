package com.instituto_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    List<Aula> findByCursoId(Long id);
}
