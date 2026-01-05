package com.rrhh.sistema.service;

import com.rrhh.sistema.model.RegistroSolicitud;
import com.rrhh.sistema.model.TipoServicio;
import com.rrhh.sistema.model.Usuario;
import com.rrhh.sistema.repository.RegistroSolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroSolicitudService {

    private final RegistroSolicitudRepository registroRepository;
    private final UsuarioService usuarioService; // Reutilizamos tu servicio de usuarios
    private final TipoServicioService tipoServicioService; // Reutilizamos tu servicio de tipos

    // 1. CREAR UNA NUEVA SOLICITUD (Cuando el empleado da click al boton)
    public RegistroSolicitud registrarSolicitud(String username, Long servicioId) {
        // A. Buscamos al usuario (Si no existe, el metodo lanza error automáticamente)
        Usuario usuario = usuarioService.buscarPorUsername(username);

        // B. Buscamos el servicio (Si no existe, lanza error)
        TipoServicio servicio = tipoServicioService.obtenerPorId(servicioId);

        // C. Creamos el registro
        RegistroSolicitud registro = new RegistroSolicitud();
        registro.setUsuario(usuario);
        registro.setTipoServicio(servicio);
        // La fecha se pone sola gracias al @PrePersist que pusimos en la Entidad

        return registroRepository.save(registro);
    }

    // 2. LISTAR HISTORIAL DE UN EMPLEADO
    public List<RegistroSolicitud> listarPorUsuario(String username) {
        return registroRepository.findByUsuarioUsernameOrderByFechaHoraDesc(username);
    }

    // 3. REPORTE PARA EL JEFE: Conteo Global
    public List<Object[]> obtenerEstadisticasGlobales() {
        return registroRepository.contarSolicitudesGlobales();
    }

    // 4. REPORTE PARA EL JEFE: Conteo de un empleado específico
    public List<Object[]> obtenerEstadisticasPorUsuario(String username) {
        return registroRepository.contarSolicitudesPorUsuario(username);
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