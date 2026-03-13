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

    @Query(value = """
            SELECT 
                a.nombre_completo,
                a.apellidos,
                c.nombre,
                au.codigo AS aula,
                i.fecha_creacion,
                ei.nombre
            FROM inscripciones i
            JOIN alumno a ON i.id_alumno = a.id
            JOIN aula au ON i.id_aula = au.id
            JOIN curso c ON au.id_curso = c.id
            JOIN estado_inscripcion ei ON i.estado_id = ei.id
            WHERE i.estado_id = 2
            ORDER BY i.fecha_creacion DESC
            LIMIT 5;

            """, nativeQuery = true)
    List<Object[]> obtenerInscripcionesRecientes();
}