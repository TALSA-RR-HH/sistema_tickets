package com.rrhh.sistema.controller;

import com.rrhh.sistema.model.TipoServicio;
import com.rrhh.sistema.service.TipoServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    // Endpoint para que el Frontend sepa qué botones pintar
    @GetMapping
    public ResponseEntity<List<TipoServicio>> listarServicios() {
        return ResponseEntity.ok(tipoServicioService.listarServiciosActivos());
    }
}
