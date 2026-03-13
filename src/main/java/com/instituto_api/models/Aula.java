package com.instituto_api.models;

import java.time.LocalDate;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

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

    @Column(name = "hora_inicio")
    @JsonProperty("hora_inicio")
    private String horaInicio;

    @Column(name = "hora_fin")
    @JsonProperty("hora_fin")
    private String horaFin;

    @ManyToOne
    @JoinColumn(name = "idProfesor", nullable = false)
    @JsonProperty("id_profesor")
    private Profesores idProfesor;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    @JsonProperty("id_curso")
    private Curso curso;

    @Column(name = "link_meet")
    private String linkMeet;

    @Column(name = "link_classroom")
    private String linkClassroom;


    public Aula(Long id, String codigo, String duracion, Set<Dia> dias, Long cantidad, LocalDate fechaInicio, LocalDate fechaFin, Profesores idProfesor, Curso curso, String linkMeet, String linkClassroom, String horaInicio, String horaFin) {
        this.id = id;
        this.codigo = codigo;
        this.duracion = duracion;
        this.cantidad = cantidad;
        this.dias = dias;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idProfesor = idProfesor;
        this.curso = curso;
        this.linkMeet = linkMeet;
        this.linkClassroom = linkClassroom;
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
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getLinkMeet() { return linkMeet; }
    public void setLinkMeet(String linkMeet) { this.linkMeet = linkMeet; }
    public String getLinkClassroom() { return linkClassroom; }
    public void setLinkClassroom(String linkClassroom) { this.linkClassroom = linkClassroom; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
}
