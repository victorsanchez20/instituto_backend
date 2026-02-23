package com.instituto_api.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.instituto_api.dto.InscripcionPorAulaDTO;
import com.instituto_api.models.Alumno;
import com.instituto_api.models.Aula;
import com.instituto_api.models.EstadoInscripcion;
import com.instituto_api.models.Inscripcion;
import com.instituto_api.models.InscripcionRequest;
import com.instituto_api.repositories.AlumnoRepository;
import com.instituto_api.repositories.AulaRepository;
import com.instituto_api.repositories.EstadoInscripcionRepository;
import com.instituto_api.repositories.InscripcionRepository;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstadoInscripcionRepository estadoRepository;
    private final AlumnoRepository alumnoRepository;
    private final AulaRepository aulaRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository,
                            EstadoInscripcionRepository estadoRepository,
                            AlumnoRepository alumnoRepository,
                            AulaRepository aulaRepository
    ) {
        this.inscripcionRepository = inscripcionRepository;
        this.estadoRepository = estadoRepository;
        this.alumnoRepository = alumnoRepository;
        this.aulaRepository = aulaRepository;
    }

    public List<Inscripcion> getAllInscripciones() {
        return this.inscripcionRepository.findAll();
    } 

    public Inscripcion create(InscripcionRequest request) {

        System.out.println("AlumnoId recibido: " + request.getAlumnoId());
        System.out.println("AulaId recibido: " + request.getAulaId());

        // 🔎 Buscar alumno
        Alumno alumno = alumnoRepository.findById(request.getAlumnoId())
                .orElseThrow(() -> new RuntimeException("Alumno no existe"));

        // 🔎 Buscar aula
        Aula aula = aulaRepository.findById(request.getAulaId())
                .orElseThrow(() -> new RuntimeException("Aula no existe"));

        Long cursoId = aula.getCurso().getId();

        // ❌ Ya inscrito en esta misma aula
        if (inscripcionRepository.existsByAlumno_IdAndAula_Id(
                alumno.getId(), aula.getId())) {
            throw new RuntimeException("Ya estás inscrito en esta aula");
        }

        // ❌ Ya inscrito en otro horario del mismo curso
        if (inscripcionRepository.alumnoYaInscritoEnCurso(alumno.getId(), cursoId)) {
            throw new RuntimeException("Ya estás inscrito en otro horario de este curso");
        }


        // estado PEN
        EstadoInscripcion estadoPendiente = estadoRepository
                .findByCodigo("PEN")
                .orElseThrow(() -> new RuntimeException("Estado PEN no existe"));

        // crear inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setAlumno(alumno);
        inscripcion.setAula(aula);
        inscripcion.setEstado(estadoPendiente);
        inscripcion.setFechaCreacion(LocalDate.now());

        Inscripcion guardada = inscripcionRepository.save(inscripcion);

        return guardada;
    }

    public void deleteById(Long id) {
        this.inscripcionRepository.deleteById(id);
    }

    public List<Inscripcion> getInscripcionByAlumno(Long id) {
        return this.inscripcionRepository.findByAlumno_Id(id);
    }

    public void cancelar(Long inscripcionId) {
        Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new RuntimeException("Inscripción no existe"));

        inscripcionRepository.delete(inscripcion);
    }

    public Inscripcion getInscriptionById(Long id) {
        return this.inscripcionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inscripcion no encontrada con id: " + id));
    }

    public List<Map<String, Object>> obtenerCantidadPorAula() {

        List<Object[]> datos = inscripcionRepository.contarInscritosPorAula();

        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Object[] fila : datos) {
            Map<String, Object> registro = new HashMap<>();
            registro.put("idAula", fila[0]);
            registro.put("cantidad", fila[1]);

            resultado.add(registro);
        }

        return resultado;
    }

    public List<InscripcionPorAulaDTO> obtenerPorAula(Long aulaId) {

        List<Inscripcion> lista = inscripcionRepository.findByAula_Id(aulaId);

        return lista.stream().map(i -> new InscripcionPorAulaDTO(
                i.getIdInscripcion(),
                i.getAlumno().getNombres(),
                i.getAlumno().getApellidos(),
                i.getAlumno().getEmail(),
                i.getAula().getIdProfesor().getNombre(),
                i.getAula().getIdProfesor().getApellido(),
                i.getAula().getCodigo(),
                i.getEstado().getNombre(),
                i.getFechaCreacion().toString()
        )).toList();
    }

    public void cambiarEstado(Long idInscripcion, Long idEstado) {

        Inscripcion inscripcion = inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        EstadoInscripcion estado = estadoRepository.findById(idEstado)
                .orElseThrow(() -> new RuntimeException("Estado no existe"));

        inscripcion.setEstado(estado);

        inscripcionRepository.save(inscripcion);
    }
}