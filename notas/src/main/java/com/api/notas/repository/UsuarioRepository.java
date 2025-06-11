package com.api.notas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.notas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { // Repositorio para manejar las operaciones CRUD de usuarios
    Optional<Usuario> findByEmail(String email); // Método para encontrar un usuario por su email
}
