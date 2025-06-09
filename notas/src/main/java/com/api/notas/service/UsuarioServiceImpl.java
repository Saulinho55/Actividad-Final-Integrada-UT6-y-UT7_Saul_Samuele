package com.api.notas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.notas.model.Usuario;
import com.api.notas.repository.UsuarioRepository;


@Service
@Transactional
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService {
    
    public UsuarioServiceImpl(UsuarioRepository repo) {
        super(repo);
    }
    
    @Override
    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        if (!repo.existsById(id)) { // repo debe estar protegido en la clase padre
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe.");
        }
        usuario.setId(id);
        return repo.save(usuario);
    }
}
