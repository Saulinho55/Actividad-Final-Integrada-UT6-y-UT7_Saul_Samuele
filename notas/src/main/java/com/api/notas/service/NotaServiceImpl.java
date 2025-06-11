package com.api.notas.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.notas.model.Nota;
import com.api.notas.repository.NotaRepository;

@Service
public class NotaServiceImpl extends AbstractCrudService<Nota, Long> implements NotaService { // Implementación del servicio de notas, extiende AbstractCrudService para heredar los métodos CRUD y define el método específico para encontrar notas por usuario
    private final NotaRepository notaRepository;

    public NotaServiceImpl(NotaRepository notaRepository) { //Constructor que recibe el repositorio de notas
        super(notaRepository);
        this.notaRepository = notaRepository;
    }

    @Override
    public List<Nota> findByUsuarioId(Long usuarioId, Sort sort) { // Método para encontrar notas por ID de usuario con ordenamiento
        return notaRepository.findByUsuarioId(usuarioId, sort); //Devuelve una lista de notas asociadas al usuario con el ID proporcionado, ordenadas según el parámetro sort
    }
}
