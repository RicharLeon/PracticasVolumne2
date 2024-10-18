package com.example.practicas.models.dao;

import com.example.practicas.models.dto.CodigosQrConsultaDTO;
import com.example.practicas.models.entity.CodigosQr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICodigosQrDao extends JpaRepository<CodigosQr, Long> {
    @Query("select new com.example.practicas.models.dto.CodigosQrConsultaDTO(ie.idEmpleado, ie.nombre, c.cogigoQR, c.fecha, c.estado, c.cantidad_consumida) from CodigosQr c " +
            "inner join c.empleado ie " +
            "where ie.idEmpleado = ?1"

    )
    List<CodigosQrConsultaDTO> consultaQrEmpleado(Long id);

    @Query("select new com.example.practicas.models.dto.CodigosQrConsultaDTO(ie.idEmpleado, ie.nombre, c.cogigoQR, c.fecha, c.estado, c.cantidad_consumida) from CodigosQr c " +
            "inner join c.empleado ie " +
            "where ie.idEmpleado = ?1 and c.estado = true "

    )
    Optional<CodigosQrConsultaDTO> consultaQrEmpleadoActivo(Long id);

   List<CodigosQr> findByIdEmpleadoAndEstado(Long idEmpleado, boolean estado);

}
