package com.rrhh.sistema.repository;

import com.rrhh.sistema.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar un usuario por su nombre de usuario
    // Devuelve un "Optional" porque puede que el usuario no exista.
    Optional<Usuario> findByUsername(String username);

    // Verificar si un usuario existe para no crear duplicados
    boolean existsByUsername(String username);
}
