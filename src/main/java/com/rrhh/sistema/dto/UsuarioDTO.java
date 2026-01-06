package com.rrhh.sistema.dto;

import com.rrhh.sistema.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String nombreCompleto;
    private Rol rol;
}
