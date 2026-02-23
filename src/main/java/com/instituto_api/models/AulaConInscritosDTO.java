package com.instituto_api.models;

import java.time.LocalDate;

public class AulaConInscritosDTO {
    private Long id;
    private String codigo;
    private String duracion;
    private Long cantidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long inscritos;

    public AulaConInscritosDTO(Aula aula, Long inscritos) {
        this.id = aula.getId();
        this.codigo = aula.getCodigo();
        this.duracion = aula.getDuracion();
        this.cantidad = aula.getCantidad();
        this.fechaInicio = aula.getFechaInicio();
        this.fechaFin = aula.getFechaFin();
        this.inscritos = inscritos;
    }

    public AulaConInscritosDTO() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getInscritos() {
        return inscritos;
    }

    public void setInscritos(Long inscritos) {
        this.inscritos = inscritos;
    }

    
}
