package com.rrhh.sistema.service;

import com.rrhh.sistema.model.TipoServicio;
import com.rrhh.sistema.repository.TipoServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    // Listar todos los tipos de servicios activos
    public List<TipoServicio> listarServiciosActivos() {
        return tipoServicioRepository.findByDisponibleTrue();
    }

    // Obtener un tipo de servicio por su ID
    public TipoServicio obtenerPorId(Long id) {
        return tipoServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado..."));
    }
}
