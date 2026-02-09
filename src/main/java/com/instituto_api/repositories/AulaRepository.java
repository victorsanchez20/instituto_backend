package com.instituto_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

}
