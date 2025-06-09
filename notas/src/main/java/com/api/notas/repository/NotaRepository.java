package com.api.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.notas.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByUsuarioId(Long usuarioId, Sort sort);
}
