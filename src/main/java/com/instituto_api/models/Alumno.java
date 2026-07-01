package com.instituto_api.models;

import java.time.LocalDate;

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

    @Column(nullable = false)
    private String apellidos;

    @Column(name = "nombre_completo", nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String password;

    private String documento;

    private String telefono;

    private LocalDate fechaNacimiento;

    private String direccion;

    private String genero;

    public Alumno() {}

    public Alumno(String apellidos, String nombres, String email, String usuario, String password,
                  String documento, String telefono, LocalDate fechaNacimiento, String direccion, String genero) {
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
        this.documento = documento;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.genero = genero;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
