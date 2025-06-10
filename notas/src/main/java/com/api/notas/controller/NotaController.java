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
    public List<Nota> getAllNotas(@RequestParam(required = false) Long usuarioId,
                                @RequestParam(defaultValue = "asc") String order) {
        if (usuarioId != null) {
            Sort sort = order.equalsIgnoreCase("desc")? Sort.by("id").descending(): Sort.by("id").ascending();
            return notaService.findByUsuarioId(usuarioId, sort);
        } else {
            return notaService.getAll();
        }
    }

    @GetMapping("/{id}")
    public Nota getById(@PathVariable Long id) {
        return notaService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Nota createNota(@RequestParam @Positive Long usuarioId, @Valid @RequestBody Nota nota) {
        Usuario usuario = usuarioService.getById(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario ha sido encontrado"));
        nota.setUsuario(usuario);
        return notaService.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable @Positive Long id, @Valid @RequestBody Nota nota) {
        Nota notas = notaService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "la nota no fue encontrada"));
        notas.setTitulo(nota.getTitulo());
        notas.setContenido(nota.getContenido());
        return notaService.update(id, notas);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        notaService.deleteById(id);
    }
}
