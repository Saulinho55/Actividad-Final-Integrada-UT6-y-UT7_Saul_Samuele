package com.api.notas.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.api.notas.model.Usuario;
import com.api.notas.repository.UsuarioRepository;
import com.api.notas.service.AbstractCrudService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService { // Implementación del servicio de usuarios que extiende el servicio AbstractCrudService para heredar los métodos CRUD y define el método específico para iniciar sesión

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository repo) { // Constructor que recibe el repositorio de usuarios
        super(repo);
        this.usuarioRepository = repo;
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario usuario) { // Método para actualizar un usuario existente
        Usuario usuarioactualizado = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con ID " + id + " no existe.")); // Busca el usuario por ID, si no existe lanza una excepción con estado NOT_FOUND
        usuarioactualizado.setNombre(usuario.getNombre()); // Actualiza el nombre del usuario
        usuarioactualizado.setEmail(usuario.getEmail()); // Actualiza el email del usuario
        usuarioactualizado.setpasswordHash(usuario.getpasswordHash()); // Actualiza el hash de la contraseña del usuario
            return usuarioRepository.save(usuarioactualizado); // Guarda el usuario actualizado y lo retorna
        }
    

    @Override
    @Transactional
    public Usuario signIn(String email, String password) { // Método para iniciar sesión, recibe email y contraseña, y devuelve un Usuario
        String contraseñahash = hashPassword(password); // Hashea la contraseña utilizando el método hashPassword
        Usuario usuariocreado = new Usuario(); // Crea una nueva instancia de Usuario
        usuariocreado.setNombre(email.split("@")[0]); // Establece el nombre del usuario como la parte local del correo
        usuariocreado.setEmail(email); // Establece el email del usuario
        usuariocreado.setpasswordHash(contraseñahash); // Establece el hash de la contraseña del usuario
        return usuarioRepository.save(usuariocreado); // Guarda el usuario creado y lo retorna
    }

    private String hashPassword(String password) { // Método para hashear la contraseña utilizando SHA-256
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error.  No se pudo hashear la contraseña", e);
        }
    }

    private String bytesToHex(byte[] bytes) { // Método para convertir un array de bytes a una cadena hexadecimal
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
