package com.instituto_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto_api.models.Anuncio;
import com.instituto_api.services.AnuncioService;

@RestController
@RequestMapping("/api/instituto/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @GetMapping
    public List<Anuncio> listarTodos() {
        return anuncioService.getAll();
    }

    @GetMapping("/activos")
    public List<Anuncio> listarActivos() {
        return anuncioService.getActivos();
    }

    @PostMapping
    public Anuncio crear(@RequestBody Anuncio anuncio) {
        return anuncioService.save(anuncio);
    }

    @PutMapping("/{id}")
    public Anuncio actualizar(@PathVariable Long id, @RequestBody Anuncio anuncio) {
        return anuncioService.update(id, anuncio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        anuncioService.delete(id);
    }
}
