package com.api.notas.service;

import com.api.notas.model.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long> { // Interfaz para el servicio de usuarios, extiende CrudService para heredar los métodos CRUD
    Usuario signIn(String email, String password); // Método para iniciar sesión, recibe email y contraseña y devuelve un Usuario
}
