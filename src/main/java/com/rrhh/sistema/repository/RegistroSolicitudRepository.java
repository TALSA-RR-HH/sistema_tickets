package com.rrhh.sistema.repository;

import com.rrhh.sistema.model.RegistroSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroSolicitudRepository extends JpaRepository<RegistroSolicitud,Long> {

    // Buscar todas las solicitudes de un usuario específico ordenadas por fecha y hora descendente
    List<RegistroSolicitud> findByUsuarioUsernameOrderByFechaHoraDesc(String username);

    // Buscar todas las solicitudes de un usuario específico ordenadas por fecha y hora descendente
    List<RegistroSolicitud> findByUsuarioIdOrderByFechaHoraDesc(Long usuarioId);

    // Contar cuántas solicitudes ha hecho un usuario específico
    long countByUsuarioUsername(String username);

    // Contar cuántas solicitudes ha hecho un usuario específico para un servicio específico
    long countByUsuarioUsernameAndTipoServicioNombreServicio(String username, String nombreServicio);

    // Buscar todas las solicitudes de un usuario específico para un servicio específico ordenadas por fecha y hora descendente
    List<RegistroSolicitud> findByUsuarioUsernameAndTipoServicioNombreServicioOrderByFechaHoraDesc(String username, String nombreServicio);
    // Contar solicitudes agrupadas por tipo de servicio para un usuario específico
    @Query("SELECT r.tipoServicio.nombreServicio, COUNT(r) " +
            "FROM RegistroSolicitud r " +
            "WHERE r.usuario.username = :username " +
            "GROUP BY r.tipoServicio.nombreServicio")
    List<Object[]> contarSolicitudesPorUsuario(String username);

    @Query("SELECT r.tipoServicio.nombreServicio, COUNT(r) " +
           "FROM RegistroSolicitud r " +
           "GROUP BY r.tipoServicio.nombreServicio")
    List<Object[]> contarSolicitudesGlobales();

    // Buscar por nombre de servicio (Ej: "Boletas") ordenado por fecha
    List<RegistroSolicitud> findByTipoServicioNombreServicioOrderByFechaHoraDesc(String nombreServicio);

    // Agrega esto en la interfaz
    List<RegistroSolicitud> findAllByOrderByFechaHoraDesc();
}
