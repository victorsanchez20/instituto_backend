package com.instituto_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.Inscripcion;
import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>  {
        /**
         * Busca todas las inscripciones usando el ID del alumno.
         * Spring entiende que debe entrar al objeto 'Alumno' y filtrar por su 'idAlumno'.
         */
        List<Inscripcion> findByAlumno_Id(Long id);
    }
