package com.instituto_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.Profesores;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesores, Long> {
    
    @Query("SELECT COUNT(*) FROM Profesores")
    Long totalProfesores();

}
