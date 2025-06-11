package com.api.notas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.notas.model.Usuario;
import com.api.notas.service.UsuarioService;

@RestController // Controlador REST para manejar las solicitudes relacionadas con los usuarios
@RequestMapping("/api/v2") // Controlador REST para manejar las solicitudes relacionadas con los usuarios
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;

    public UsuarioControllerV2(UsuarioService usuarioService) { // Constructor para inyectar servicio de usuarios
        this.usuarioService = usuarioService;
    }

    @PostMapping("/sign-in") // Ruta para iniciar sesión
    public Usuario signIn(@RequestParam String email, @RequestParam String password) { // Método de SignIn
        return usuarioService.signIn(email, password);
    }
}
