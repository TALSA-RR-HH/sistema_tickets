package com.rrhh.sistema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usaremos el DNI como username, es único y fácil de recordar
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;    // Almacenaremos el hash de la contraseña

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;  // ROLE_USER o ROLE_ADMIN

    private boolean activo = true;  // Por defecto, el usuario está activo
}
