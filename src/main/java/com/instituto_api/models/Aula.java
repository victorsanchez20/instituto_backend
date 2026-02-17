package com.instituto_api.models;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    private String codigo;


    @Column(name = "duracion", nullable = false)
    private String duracion;

    @ElementCollection(targetClass = Dia.class)
    @CollectionTable(name = "aula_dias", joinColumns = @JoinColumn(name = "aula_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "dia")
    private Set<Dia> dias;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "fecha_inicio", nullable = false)
    @JsonProperty("fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false) 
    @JsonProperty("fecha_fin")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "idProfesor", nullable = false)
    @JsonProperty("id_profesor")
    private Profesores idProfesor;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    @JsonProperty("id_curso")
    private Curso curso;

    public Aula(Long id, String codigo, String duracion, Set<Dia> dias, Long cantidad, LocalDate fechaInicio, LocalDate fechaFin, Profesores idProfesor, Curso curso) {
        this.id = id;
        this.codigo = codigo;
        this.duracion = duracion;
        this.cantidad = cantidad;
        this.dias = dias;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idProfesor = idProfesor;
        this.curso = curso;
    }
    public Aula() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDuracion() { return duracion; }
    public Long getCantidad() { return cantidad; }
    public void setCantidad(Long cantidad) { this.cantidad = cantidad; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public Set<Dia> getDias() { return dias; }
    public void setDias(Set<Dia> dias) { this.dias = dias; }
    public Profesores getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Profesores idProfesor) { this.idProfesor = idProfesor; }
    public Curso getIdCurso() { return curso; }
    public void setIdCurso(Curso idCurso) { this.curso = idCurso; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
}
