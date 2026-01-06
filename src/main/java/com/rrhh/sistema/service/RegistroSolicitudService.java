package com.rrhh.sistema.service;

import com.rrhh.sistema.dto.ReporteDTO;
import com.rrhh.sistema.dto.SolicitudDTO;
import com.rrhh.sistema.model.RegistroSolicitud;
import com.rrhh.sistema.model.TipoServicio;
import com.rrhh.sistema.model.Usuario;
import com.rrhh.sistema.repository.RegistroSolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroSolicitudService {

    private final RegistroSolicitudRepository registroRepository;
    private final UsuarioService usuarioService; // Reutilizamos tu servicio de usuarios
    private final TipoServicioService tipoServicioService; // Reutilizamos tu servicio de tipos

    // 1. CREAR UNA NUEVA SOLICITUD (Cuando el empleado da click al boton)
    public SolicitudDTO registrarSolicitud(String username, Long servicioId) {

        Usuario usuario = usuarioService.buscarPorUsername(username);
        TipoServicio servicio = tipoServicioService.obtenerPorId(servicioId);

        RegistroSolicitud registro = new RegistroSolicitud();
        registro.setUsuario(usuario);
        registro.setTipoServicio(servicio);

        RegistroSolicitud guardado = registroRepository.save(registro);

        return new SolicitudDTO(
                guardado.getId(),
                guardado.getUsuario().getNombreCompleto(),
                guardado.getUsuario().getUsername(),
                guardado.getTipoServicio().getNombreServicio(),
                guardado.getFechaHora()
        );
    }

    // 2. LISTAR HISTORIAL DE UN EMPLEADO
    public List<SolicitudDTO> listarPorUsuario(String username) {
        List<RegistroSolicitud> entidades = registroRepository.findByUsuarioUsernameOrderByFechaHoraDesc(username);

        return entidades.stream()
                .map(registro -> new SolicitudDTO(
                        registro.getId(),
                        registro.getUsuario().getNombreCompleto(),
                        registro.getUsuario().getUsername(),
                        registro.getTipoServicio().getNombreServicio(),
                        registro.getFechaHora()
                ))
                .collect(Collectors.toList());
    }

    // 3. REPORTE PARA EL JEFE: Conteo Global
    public List<Object[]> obtenerEstadisticasGlobales() {
        return registroRepository.contarSolicitudesGlobales();
    }

    // 4. REPORTE PARA EL JEFE: Conteo de un empleado específico
    public List<ReporteDTO> obtenerEstadisticasPorUsuario(String username) {
        List<Object[]> resultados = registroRepository.contarSolicitudesPorUsuario(username);

        return resultados.stream()
                .map(fila -> new ReporteDTO(
                        (String) fila[0], // La posición 0 es el nombre (Boletas)
                        (Long) fila[1]    // La posición 1 es la cuenta (3)
                ))
                .collect(Collectors.toList());
    }

    /* Otras funciones útiles:
    // Permite al empleado filtrar su historial (Ej: Ver solo "Boletas")
    public List<RegistroSolicitud> listarPorUsuarioYServicio(String username, String nombreServicio) {
        return registroRepository.findByUsuarioUsernameAndTipoServicioNombreServicioOrderByFechaHoraDesc(username, nombreServicio);
    }

    // Devuelve solo el número total (Ej: Para mostrar un badge "Tienes 5 solicitudes")
    public long contarTotalPorUsuario(String username) {
        return registroRepository.countByUsuarioUsername(username);
    }*/
}