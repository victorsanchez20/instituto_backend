package com.instituto_api.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.instituto_api.models.Aula;
import com.instituto_api.models.Curso;
import com.instituto_api.services.AulaService;
import com.instituto_api.services.CursoService;

import java.nio.file.Path;


@RestController
@RequestMapping("api/instituto/curso")
public class CursoController {

    private final CursoService cursoService;
    private final AulaService aulaService;

    public CursoController(CursoService cursoService, AulaService aulaService) {
        this.cursoService = cursoService;
        this.aulaService = aulaService;
    }

    @GetMapping
    public List<Curso> listarCurso() {
        return this.cursoService.getAllCursos();
    }

   @PostMapping("/guardar-con-imagen")
    public Curso guardarCursoConImagen(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("imagen") MultipartFile imagen
    ) {
        try {
            // carpeta donde se guardan las imágenes
            String uploadDir = "uploads/cursos/";
            Files.createDirectories(Paths.get(uploadDir));

            // nombre único del archivo
            String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
            Path rutaArchivo = Paths.get(uploadDir + nombreArchivo);

            // guardar archivo
            Files.write(rutaArchivo, imagen.getBytes());

            // guardar en BD
            Curso curso = new Curso();
            curso.setNombre(nombre);
            curso.setDescripcion(descripcion);
            curso.setImagen("/uploads/cursos/" + nombreArchivo);

            return cursoService.save(curso);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar curso", e);
        }
    }


    @DeleteMapping("/{id}")
    public void eliminarCurso(@PathVariable Long id) {
        this.cursoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Curso actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return this.cursoService.save(curso);
    }

    @GetMapping("/{id}")
    public Curso obtenerCurso(@PathVariable Long id) {
        return this.cursoService.getById(id);
    }

    @GetMapping("/{id}/aulas")
    public ResponseEntity<List<Aula>> getAulasByCurso(@PathVariable("id") Long cursoId) {
        List<Aula> aulas = aulaService.obtenerAulasPorCurso(cursoId);
        return ResponseEntity.ok(aulas);
    }
    
}
