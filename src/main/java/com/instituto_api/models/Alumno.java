package com.instituto_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "apellidos" , nullable = false)
    private String apellidos;
    @Column(name = "nombre_completo", nullable = false)
    private String nombres;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "usuario", nullable = false)
    private String usuario;
    @Column(name = "password", nullable = false)
    private String password;

    public Alumno(Long id, String apellidos, String nombres, String email, String usuario, String password) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
    }

    public Alumno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
