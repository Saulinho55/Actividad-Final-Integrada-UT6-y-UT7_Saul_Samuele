package com.api.notas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.notas.model.Usuario;
import com.api.notas.service.UsuarioService;

@RestController
@RequestMapping("/api/v2")
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;

    public UsuarioControllerV2(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/sign-in")
    public Usuario signIn(@RequestParam String email, @RequestParam String password) {
        return usuarioService.signIn(email, password);
    }
}
