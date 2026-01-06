package com.rrhh.sistema.controller;

import com.rrhh.sistema.dto.UsuarioDTO;
import com.rrhh.sistema.model.Usuario;
import com.rrhh.sistema.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        try {
            // Llamamos al servicio que devuelve el DTO limpio
            UsuarioDTO usuarioDto = usuarioService.login(username, password);
            return ResponseEntity.ok(usuarioDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
