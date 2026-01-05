package com.rrhh.sistema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipos_servicios")
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_servicio", nullable = false, unique = true)
    private String nombreServicio;  // Ej: "Boletas"

    private String codigoIcono; // Ej: "fa fa-cog"

    private boolean disponible = true;  // Por defecto, el servicio está disponible
}
