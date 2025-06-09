package com.api.notas.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.notas.service.NotaService;
import com.api.notas.service.UsuarioService;

@RestController
@Validated
@RequestMapping("/api/v1/notas")
public class NotaController {
    private final NotaService notaService;
    private final UsuarioService usuarioService;

    public NotaController(NotaService notaService, UsuarioService usuarioService) {
        this.notaService = notaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Nota getById
}
