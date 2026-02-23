package com.instituto_api.dto;

public class InscripcionPorAulaDTO {

    private Long idInscripcion;

    // Alumno
    private String nombresAlumno;
    private String apellidosAlumno;
    private String emailAlumno;

    // Profesor
    private String nombreProfesor;
    private String apellidoProfesor;

    // Aula
    private String codigoAula;

    // Estado
    private String estado;

    // Fecha
    private String fechaCreacion;

    public InscripcionPorAulaDTO(Long idInscripcion,
                                 String nombresAlumno,
                                 String apellidosAlumno,
                                 String emailAlumno,
                                 String nombreProfesor,
                                 String apellidoProfesor,
                                 String codigoAula,
                                 String estado,
                                 String fechaCreacion) {

        this.idInscripcion = idInscripcion;
        this.nombresAlumno = nombresAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.emailAlumno = emailAlumno;
        this.nombreProfesor = nombreProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.codigoAula = codigoAula;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdInscripcion() { return idInscripcion; }
    public String getNombresAlumno() { return nombresAlumno; }
    public String getApellidosAlumno() { return apellidosAlumno; }
    public String getEmailAlumno() { return emailAlumno; }
    public String getNombreProfesor() { return nombreProfesor; }
    public String getApellidoProfesor() { return apellidoProfesor; }
    public String getCodigoAula() { return codigoAula; }
    public String getEstado() { return estado; }
    public String getFechaCreacion() { return fechaCreacion; }
}