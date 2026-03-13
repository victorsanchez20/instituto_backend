package com.instituto_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT COUNT(c) FROM Curso c")
    Long totalCursos();

    @Query("""
        SELECT 
        c.nombre,
        COUNT(i)
    FROM Inscripcion i
    JOIN i.aula a
    JOIN a.curso c
    WHERE i.estado.id = 3
    GROUP BY c.id, c.nombre
    ORDER BY COUNT(i) DESC
    """)
    List<Object[]> totalInscritosPorCurso();
}
