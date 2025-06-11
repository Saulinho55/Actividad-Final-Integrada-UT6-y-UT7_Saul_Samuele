package com.api.notas.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.api.notas.model.Nota;

public interface NotaService extends CrudService<Nota, Long> { // Interfaz para el servicio de notas, extiende CrudService para heredar los métodos CRUD
    List<Nota> findByUsuarioId(Long usuarioId, Sort sort); // Método para encontrar notas por ID de usuario con ordenamiento
}
