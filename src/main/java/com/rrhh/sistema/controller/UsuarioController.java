package com.rrhh.sistema.controller;

import com.rrhh.sistema.model.Usuario;
import com.rrhh.sistema.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuarioLogin) {
        try {
            // Buscamos al usuario en la BD
            Usuario usuarioEncontrado = usuarioService.buscarPorUsername(usuarioLogin.getUsername());

            // Verificamos la contraseña (comparación simple por ahora)
            if (usuarioEncontrado.getPassword().equals(usuarioLogin.getPassword())) {
                // Si coincide, devolvemos el usuario completo (para que el Frontend sepa si es ADMIN o USER)
                return ResponseEntity.ok(usuarioEncontrado);
            } else {
                return ResponseEntity.status(401).body("Contraseña incorrecta");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
