package com.rrhh.sistema.controller;

import com.rrhh.sistema.dto.ReporteDTO;
import com.rrhh.sistema.dto.SolicitudDTO;
import com.rrhh.sistema.model.RegistroSolicitud;
import com.rrhh.sistema.service.RegistroSolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SolicitudController {

    private final RegistroSolicitudService solicitudService;

    @PostMapping    // POST /api/solicitudes?username=12345678&servicioId=1
    public ResponseEntity<?> crearSolicitud(@RequestParam String username, @RequestParam Long servicioId) {
        try {
            SolicitudDTO nuevaSolicitud = solicitudService.registrarSolicitud(username, servicioId);
            return ResponseEntity.ok(nuevaSolicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/historial")   // GET /api/solicitudes/historial?username=12345678
    public ResponseEntity<List<SolicitudDTO>> verHistorial(@RequestParam String username) {
        List<SolicitudDTO> historial = solicitudService.listarPorUsuario(username);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/reporte-global")  // GET /api/solicitudes/reporte-global
    public ResponseEntity<List<Object[]>> verReporteGlobal() {
        return ResponseEntity.ok(solicitudService.obtenerEstadisticasGlobales());
    }

    @GetMapping("/reporte-usuario") // GET /api/solicitudes/reporte-usuario?username=12345678
    public ResponseEntity<List<ReporteDTO>> verReporteUsuario(@RequestParam String username) {
        return ResponseEntity.ok(solicitudService.obtenerEstadisticasPorUsuario(username));
    }
}
