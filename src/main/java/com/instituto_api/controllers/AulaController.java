package com.instituto_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto_api.models.Aula;
import com.instituto_api.services.AulaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


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
}