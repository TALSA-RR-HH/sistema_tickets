package com.rrhh.sistema.repository;

import com.rrhh.sistema.model.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio,Long> {

    // Buscar todos los tipos de servicios que están disponibles
    List<TipoServicio> findByDisponibleTrue();

    // Verificar si un tipo de servicio existe por su nombre para evitar duplicados
    boolean existsByNombreServicio(String nombreServicio);
}
