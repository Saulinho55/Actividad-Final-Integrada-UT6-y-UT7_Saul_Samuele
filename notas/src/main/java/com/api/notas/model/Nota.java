package com.api.notas.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity // Define la entidad Nota
@Table(name = "notas") // Define el nombre de la tabla en la base de datos
public class Nota { // Clase que representa una nota en el sistema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un ID único para cada nota
    private Long id;  // Identificador único de la nota

    @NotBlank(message = "El título no puede estar en blanco") // Validación para asegurarse de que el título no esté en blanco
    private String titulo; // Título de la nota

    @Lob // Indica que el campo contenido puede ser grande (tipo texto largo)
    private String contenido;  // Contenido de la nota, puede ser un texto largo
    private LocalDateTime fechaCreacion = LocalDateTime.now(); // Fecha de creación de la nota, se inicializa con la fecha y hora actual
    @ManyToOne(optional = false) // Relación muchos a uno con la entidad Usuario, no puede ser nulo
    @JsonIgnore // Ignora este campo en la serialización JSON para evitar ciclos
    private Usuario usuario;
    // Getters y Setters para acceder y modificar los campos de la nota
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
