package com.api.notas.controller;
import com.api.notas.model.Usuario;
import com.api.notas.service.UsuarioService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

@RestController // Controlador REST para manejar las solicitudes relacionadas con los usuarios
@RequestMapping("/api/v1/usuarios") // Controlador REST para manejar las solicitudes relacionadas con los usuarios
public class UsuarioController { 

    private final UsuarioService usuarioService; // Servicio para manejar la lógica de negocio relacionada con los usuarios

    public UsuarioController(UsuarioService usuarioService) { // Constructor para inyectar servicio de usuarios
        this.usuarioService = usuarioService;
    }
    
    @GetMapping // Ruta para obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {  
        return usuarioService.getAll(); // Método para obtener todos los usuarios
    }

    @GetMapping("/{id}") // Ruta para obtener un usuario específico por su ID
    public Usuario getUsuarioById(@PathVariable @Positive Long id) { // Método para obtener un usuario por su ID
        return usuarioService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")); // Si no se encuentra el usuario, se lanza una excepción con estado NOT_FOUND
    }

    @PostMapping // Ruta para crear un nuevo usuario
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) { // Método para crear un nuevo usuario
        return usuarioService.save(usuario); // Método para guardar un nuevo usuario
    }

    @PutMapping("/{id}") // Ruta para actualizar un usuario existente por su ID
    public Usuario updateUsuario(@PathVariable @Positive Long id, @RequestBody Usuario usuario) { // Método para actualizar un usuario existente
        return usuarioService.update(id, usuario); // Método para actualizar un usuario existente
    }

    @DeleteMapping("/{id}") // Ruta para eliminar un usuario por su ID
    public void delete(@PathVariable @Positive Long id) { // Método para eliminar un usuario por su ID
        usuarioService.deleteById(id); // Método para eliminar un usuario por su ID
    }

}
