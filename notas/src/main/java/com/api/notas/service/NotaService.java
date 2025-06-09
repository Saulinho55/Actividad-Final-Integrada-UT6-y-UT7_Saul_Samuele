package com.api.notas.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

import com.api.notas.model.Nota;

public interface NotaService extends CrudService<Nota, Long> {
    List<Nota> findByUsuarioId(Long usuarioId, Sort sort);
}
