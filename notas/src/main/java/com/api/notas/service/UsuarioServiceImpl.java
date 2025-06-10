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
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long> implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        super(repo);
        this.usuarioRepository = repo;
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioactualizado = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con ID " + id + " no existe."));
        usuarioactualizado.setNombre(usuario.getNombre());
        usuarioactualizado.setEmail(usuario.getEmail());
        usuarioactualizado.setpasswordHash(usuario.getpasswordHash());
            return usuarioRepository.save(usuarioactualizado);
        }
    

    @Override
    @Transactional
    public Usuario signIn(String email, String password) {
        String contraseñahash = hashPassword(password);
        Usuario usuariocreado = new Usuario();
        usuariocreado.setNombre(email.split("@")[0]);
        usuariocreado.setEmail(email);
        usuariocreado.setpasswordHash(contraseñahash);
        return usuarioRepository.save(usuariocreado);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error.  No se pudo hashear la contraseña", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
