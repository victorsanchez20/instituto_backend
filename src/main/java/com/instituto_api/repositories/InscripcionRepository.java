package com.instituto_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.Inscripcion;
import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>  {
    List<Inscripcion> findByAlumno_Id(Long id);

    boolean existsByAlumno_IdAndAula_Id(Long alumnoId, Long aulaId);

    @Query("SELECT COUNT(i) > 0 FROM Inscripcion i WHERE i.alumno.id = :alumnoId AND i.aula.curso.id = :cursoId")
    boolean alumnoYaInscritoEnCurso(@Param("alumnoId") Long alumnoId, @Param("cursoId") Long cursoId);

    @Query("SELECT COUNT(i) FROM Inscripcion i WHERE i.aula.id = :aulaId")
    Long countByAulaId(@Param("aulaId") Long aulaId);

    @Query("SELECT i.aula.id, COUNT(i) FROM Inscripcion i GROUP BY i.aula.id")
    List<Object[]> contarInscritosPorAula();

    @Query("""
    SELECT i FROM Inscripcion i
    JOIN FETCH i.aula a
    JOIN FETCH a.curso
    WHERE i.alumno.id = :alumnoId
    """)
    List<Inscripcion> obtenerCursosDelAlumno(@Param("alumnoId") Long alumnoId);

    List<Inscripcion> findByAula_Id(Long aulaId);
}
