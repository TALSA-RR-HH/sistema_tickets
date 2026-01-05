package com.rrhh.sistema.service;

import com.rrhh.sistema.model.Usuario;
import com.rrhh.sistema.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Guardar o actualizar un usuario
    public Usuario guardarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioExistente.isPresent()) {
            // SI EXISTE: Actualizamos datos
            Usuario u = usuarioExistente.get();
            u.setNombreCompleto(usuario.getNombreCompleto());
            u.setRol(usuario.getRol());
            u.setActivo(true);
            return usuarioRepository.save(u);
        } else {
            // SI ES NUEVO
            if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
                usuario.setPassword(usuario.getUsername());
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
        Usuario u = buscarPorUsername(username); // Reutilizamos el método de arriba
        u.setPassword(nuevaPassword);
        usuarioRepository.save(u);
    }
}
