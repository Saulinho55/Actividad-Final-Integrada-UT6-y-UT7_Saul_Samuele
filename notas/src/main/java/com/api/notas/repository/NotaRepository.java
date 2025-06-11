package com.api.notas.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.notas.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> { // Repositorio para manejar las operaciones CRUD de notas
    List<Nota> findByUsuarioId(Long usuarioId, Sort sort); // Método para encontrar notas por ID de usuario con ordenamiento
}
