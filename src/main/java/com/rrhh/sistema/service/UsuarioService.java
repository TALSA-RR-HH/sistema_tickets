package com.rrhh.sistema.service;

import com.rrhh.sistema.dto.UsuarioDTO;
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

    public UsuarioDTO login(String username, String password) {
        // 1. Buscamos (reutilizamos tu lógica de búsqueda)
        Usuario usuario = buscarPorUsername(username);

        // 2. Validamos la contraseña
        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 3. Validamos si está activo
        if (!usuario.getActivo()) {
            throw new RuntimeException("El usuario está inactivo, contacte a RRHH");
        }

        // 4. Convertimos a DTO (Aquí quitamos el password)
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNombreCompleto(),
                usuario.getRol()
        );
    }
}
