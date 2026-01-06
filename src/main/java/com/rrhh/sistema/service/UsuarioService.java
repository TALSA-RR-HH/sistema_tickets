package com.rrhh.sistema.service;

import com.rrhh.sistema.dto.UsuarioDTO;
import com.rrhh.sistema.model.Usuario;
import com.rrhh.sistema.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Guardar o actualizar un usuario
    public Usuario guardarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioExistente.isPresent()) {
            Usuario u = usuarioExistente.get();
            u.setNombreCompleto(usuario.getNombreCompleto());
            u.setRol(usuario.getRol());
            u.setActivo(true);
            return usuarioRepository.save(u);
        } else {
            // USUARIO NUEVO
            String rawPassword = usuario.getPassword();

            // Si no hay contraseña, usamos el username (DNI)
            if (rawPassword == null || rawPassword.isEmpty()) {
                rawPassword = usuario.getUsername();
            }

            // ENCRIPTAR LA CONTRASEÑA ANTES DE GUARDAR
            usuario.setPassword(passwordEncoder.encode(rawPassword));

            if (usuario.getActivo() == null) {
                usuario.setActivo(true);
            }
            return usuarioRepository.save(usuario);
        }
    }

    // Buscar un usuario por su nombre de usuario
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado..."));
    }

    // Cambiar la contraseña de un usuario
    public void cambiarContrasena(String username, String nuevaPassword) {
        Usuario u = buscarPorUsername(username);
        // Encriptamos antes de guardar
        u.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(u);
    }

    public UsuarioDTO login(String username, String password) {
        Usuario usuario = buscarPorUsername(username);

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        if (!usuario.getActivo()) {
            throw new RuntimeException("El usuario está inactivo. Contacte a RRHH.");
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNombreCompleto(),
                usuario.getRol()
        );
    }
}
