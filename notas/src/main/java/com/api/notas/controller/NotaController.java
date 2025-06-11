package com.api.notas.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.notas.model.Nota;
import com.api.notas.model.Usuario;
import com.api.notas.service.NotaService;
import com.api.notas.service.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController // Controlador REST para manejar las solicitudes relacionadas con las notas
@Validated // Validación de las solicitudes entrantes
@RequestMapping("/api/v1/notas") // Ruta base para las solicitudes de notas

public class NotaController { // Controlador para manejar las operaciones CRUD de notas
    private final NotaService notaService;  
    private final UsuarioService usuarioService;

    public NotaController(NotaService notaService, UsuarioService usuarioService) { // Constructor para los servicios necesarios
        this.notaService = notaService;
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public List<Nota> getAllNotas(@RequestParam(required = false) Long usuarioId, // Parámetro opcional para filtrar por ID de usuario
                                @RequestParam(defaultValue = "asc") String order) {// Parámetro opcional para definir el orden de las notas
        if (usuarioId != null) { // Si se proporciona un ID de usuario, se filtran las notas por ese usuario
            Sort sort = order.equalsIgnoreCase("desc")? Sort.by("id").descending(): Sort.by("id").ascending();
            return notaService.findByUsuarioId(usuarioId, sort);
        } else { // Si no se proporciona un ID de usuario, se devuelven todas las notas
            return notaService.getAll();
        }
    }

    @GetMapping("/{id}") // Ruta para obtener una nota específica por su ID
    public Nota getById(@PathVariable @Positive Long id) { // Método para obtener una nota por su ID
        return notaService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // Si no se encuentra la nota, se lanza una excepción con estado NOT_FOUND
    }

    @PostMapping // Ruta para crear una nueva nota
    @ResponseStatus(HttpStatus.CREATED) // Estado de respuesta 201 Created al crear una nota
    public Nota createNota(@RequestParam @Positive Long usuarioId, @Valid @RequestBody Nota nota) { // Método para crear una nueva nota
        Usuario usuario = usuarioService.getById(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario ha sido encontrado")); // Se busca el usuario por su ID, si no se encuentra, se lanza una excepción
        nota.setUsuario(usuario); // Se establece el usuario asociado a la nota
        return notaService.save(nota); // Se guarda la nota y se devuelve
    }

    @PutMapping("/{id}") // Ruta para actualizar una nota existente por su ID
    public Nota update(@PathVariable @Positive Long id, @Valid @RequestBody Nota nota) { // Método para actualizar una nota existente
        Nota notas = notaService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "la nota no fue encontrada")); // Se busca la nota por su ID, si no se encuentra, se lanza una excepción
        notas.setTitulo(nota.getTitulo()); // Se actualiza el título de la nota
        notas.setContenido(nota.getContenido()); // Se actualiza el contenido de la nota
        return notaService.update(id, notas); // Se guarda la nota actualizada y se devuelve
    } 

    @DeleteMapping("/{id}") // Ruta para eliminar una nota por su ID
    public void delete(@PathVariable @Positive Long id) { // Método para eliminar una nota por su ID
        notaService.deleteById(id); // Se elimina la nota por su ID, si no se encuentra, se lanza una excepción
    }
}
