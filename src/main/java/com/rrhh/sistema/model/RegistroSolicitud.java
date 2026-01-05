package com.rrhh.sistema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registros_solicitudes")
public class RegistroSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private TipoServicio tipoServicio;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @PrePersist
    public void asignarFecha() {
        this.fechaHora = LocalDateTime.now();
    }
}
