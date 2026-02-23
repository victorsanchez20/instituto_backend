package com.instituto_api.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.instituto_api.models.Aula;
import com.instituto_api.models.AulaConInscritosDTO;
import com.instituto_api.models.Dia;
import com.instituto_api.models.Inscripcion;
import com.instituto_api.repositories.AulaRepository;
import com.instituto_api.repositories.InscripcionRepository;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final InscripcionRepository inscripcionRepository;

    public AulaService(AulaRepository aulaRepository,
                       InscripcionRepository inscripcionRepository
    ) {
        this.aulaRepository = aulaRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Aula> getAllAulas() {
        return this.aulaRepository.findAll();
    }

    public Aula save(Aula aula) {
        return this.aulaRepository.save(aula);
    }   

    public Aula readClassroomById(Long id) {
        return this.aulaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inscripcion no encontrada con id: " + id));
    }

    public Aula update(Aula aula, Long id) {
        aula.setId(id);
        return this.aulaRepository.save(aula);
    }

    public void deleteById(long id) {
        this.aulaRepository.deleteById(id);
    }

    public List<Aula> obtenerAulasPorCurso(Long cursoId) {
        return aulaRepository.findByCursoId(cursoId);
    }

    public List<AulaConInscritosDTO> listarAulasConInscritos() {

        List<Aula> aulas = aulaRepository.findAll();

        return aulas.stream().map(aula -> {
            Long total = inscripcionRepository.countByAulaId(aula.getId());
            return new AulaConInscritosDTO(aula, total);
        }).toList();
    }

    public List<Aula> aulasActivasHoy(Long alumnoId) {

        List<Inscripcion> inscripciones = inscripcionRepository.obtenerCursosDelAlumno(alumnoId);

        LocalDate hoy = LocalDate.now();
        DayOfWeek diaHoy = hoy.getDayOfWeek();

        return inscripciones.stream()
            .map(Inscripcion::getAula)
            .filter(aula ->
                !hoy.isBefore(aula.getFechaInicio()) &&
                !hoy.isAfter(aula.getFechaFin()) &&
                aula.getDias().contains(Dia.valueOf(diaHoy.name()))
            )
            .toList();
    }


}
