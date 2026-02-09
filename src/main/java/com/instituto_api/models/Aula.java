package com.instituto_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(name = "dias", nullable = false)
    private String dias;

    @ManyToOne
    @JoinColumn(name = "idProfesor", nullable = false)
    @JsonProperty("id_profesor")
    private Profesores idProfesor;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    @JsonProperty("id_curso")
    private Curso idCurso;

    public Aula(Long id, String codigo, String duracion, String dias, Profesores idProfesor, Curso idCurso) {
        this.id = id;
        this.codigo = codigo;
        this.duracion = duracion;
        this.dias = dias;
        this.idProfesor = idProfesor;
        this.idCurso = idCurso;
    }
    public Aula() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public String getDias() { return dias; }
    public void setDias(String dias) { this.dias = dias; }
    public Profesores getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Profesores idProfesor) { this.idProfesor = idProfesor; }
    public Curso getIdCurso() { return idCurso; }
    public void setIdCurso(Curso idCurso) { this.idCurso = idCurso; }
}
