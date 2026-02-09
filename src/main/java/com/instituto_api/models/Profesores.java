package com.instituto_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesores")
public class Profesores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column (name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "especialidad", nullable = false)
    private String especialidad;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "documento")
    private String docidentidad;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "password", nullable = false)
    private String password;

    public Profesores(Long id, String nombre, String apellido, String especialidad, String email, String docidentidad, String telefono,
            String usuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido =apellido;
        this.especialidad = especialidad;
        this.email = email;
        this.docidentidad = docidentidad;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
    }
    public Profesores() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDocidentidad() {
        return docidentidad;
    }
    public void setDocidentidad(String docidentidad) {
        this.docidentidad = docidentidad;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
