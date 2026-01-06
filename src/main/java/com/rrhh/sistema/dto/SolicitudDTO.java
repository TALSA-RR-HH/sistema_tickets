package com.rrhh.sistema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private Long id;
    private String usuarioNombre;
    private String username;
    private String nombreServicio;
    private LocalDateTime fechaHora;
}
