package com.api.notas.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Service;

import com.api.notas.model.Nota;
import com.api.notas.repository.NotaRepository;

@Service
public class NotaServiceImpl extends AbstractCrudService<Nota, Long> implements NotaService {
    private final NotaRepository notaRepository;

    public NotaServiceImpl(NotaRepository notaRepository) {
        super(notaRepository);
        this.notaRepository = notaRepository;
    }

    @Override
    public List<Nota> findByUsuarioId(Long usuarioId, Sort sort) {
        return notaRepository.findByUsuarioId(usuarioId, sort);
    }
}
