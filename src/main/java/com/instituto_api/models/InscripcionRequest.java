package com.instituto_api.models;

public class InscripcionRequest {

    private Long alumnoId;
    private Long aulaId;
    private Long estadoId;

    public InscripcionRequest(Long alumnoId, Long aulaId, Long estadoId) {
        this.alumnoId = alumnoId;
        this.aulaId = aulaId;
        this.estadoId = estadoId;
    }

    public InscripcionRequest() { }

    public Long getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Long alumnoId) { this.alumnoId = alumnoId; }
    public Long getAulaId() { return aulaId; }
    public void setAulaId(Long aulaId) { this.aulaId = aulaId; }
    public Long getEstadoId() { return estadoId; }
    public void setEstadoId(Long estadoId) { this.estadoId = estadoId; }
}
