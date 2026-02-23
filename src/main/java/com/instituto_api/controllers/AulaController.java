package com.instituto_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.instituto_api.models.Aula;
import com.instituto_api.models.AulaConInscritosDTO;
import com.instituto_api.services.AulaService;


@RestController
@RequestMapping("/api/instituto/aula")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping
    public List<Aula> listarAulas() {
        return this.aulaService.getAllAulas();
    }

    @GetMapping("/{id}")
    public Aula listarAulaPorId(@PathVariable Long id) {
        return this.aulaService.readClassroomById(id);
    }

    @PostMapping
    public Aula guardarAula(@RequestBody Aula aula) {
        return this.aulaService.save(aula);
    }

    @DeleteMapping("/{id}")
    public void eliminarAula(@PathVariable Long id) {
        this.aulaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Aula actualizarAula(@PathVariable Long id, @RequestBody Aula aula) {
        aula.getId();
        return this.aulaService.update(aula, id);
    }

    @GetMapping("/con-inscritos")
    public List<AulaConInscritosDTO> listar() {
        return aulaService.listarAulasConInscritos();
    }


}