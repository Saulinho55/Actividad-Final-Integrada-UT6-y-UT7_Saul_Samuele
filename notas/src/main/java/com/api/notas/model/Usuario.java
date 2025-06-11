package com.api.notas.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity // Define la entidad Usuario
@Table(name = "usuarios") // Define el nombre de la tabla en la base de datos
public class Usuario { // Clase que representa un usuario en el sistema
    @Id // Define el campo id como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un ID único para cada usuario
    private Long id; // Identificador único del usuario

    @NotBlank(message = "El nombre no puede estar en blanco") // Validación para asegurarse de que el nombre no esté en blanco
    private String nombre; // Nombre del usuario

    @Email // Validación para asegurarse de que el email tenga un formato válido
    @Column(unique = true) // Asegura que el email sea único y tenga un formato válido
    private String email;  // Email del usuario, debe ser único en la base de datos

    
    @NotBlank(message = "La contraseña no puede estar en blanco")  // Validación para asegurarse de que la contraseña no esté en blanco
    @Size(min = 7, message = "La contraseña tiene que tener al menos 7 caracteres") // Validación para asegurarse de que la contraseña tenga al menos 7 caracteres
    private String passwordHash; // Contraseña del usuario, almacenada como un hash para mayor seguridad

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // Relación uno a muchos con la entidad Nota, donde "usuario" es el campo en la entidad Nota que referencia a Usuario
    @JsonIgnore // Ignora este campo en la serialización JSON para evitar ciclos
    private List<Nota> notas = new ArrayList<>(); // Lista de notas asociadas al usuario, inicializada como una lista vacía

    public void AñadirNota(Nota nota) { // Método para añadir una nota al usuario
        notas.add(nota); // Añade la nota a la lista de notas del usuario
        nota.setUsuario(this); // Establece el usuario de la nota como este usuario
    }

    public void EliminarNota(Nota nota) { // Método para eliminar una nota del usuario
        notas.remove(nota); // Elimina la nota de la lista de notas del usuario
        nota.setUsuario(null); // Establece el usuario de la nota como nulo para romper la relación
    }
    // Getters y Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpasswordHash() {
        return passwordHash;
    }

    public void setpasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    
}
