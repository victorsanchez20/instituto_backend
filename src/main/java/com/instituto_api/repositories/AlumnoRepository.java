package com.instituto_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.instituto_api.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByUsuario(String usuario);

    @Query("SELECT COUNT(*) FROM Alumno")
    Long totalAlumnos();    
}
