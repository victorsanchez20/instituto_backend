package com.instituto_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto_api.models.Curso;
import java.util.List;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> getCursoById(Long id);

}
