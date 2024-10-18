package com.example.practicas.models.dao;

import com.example.practicas.models.dto.CambioHorarioConsultaDTO;
import com.example.practicas.models.dto.CambioHorarioPersistenciaDTO;
import com.example.practicas.models.entity.CambioHorario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICambioHorarioDao extends JpaRepository<CambioHorario, Long> {

    @Query("select new com.example.practicas.models.dto.CambioHorarioPersistenciaDTO(c.idCambioHorario, es.idEmpleado, ea.idEmpleado, ec.idEmpleado, c.estado) from CambioHorario c " +
            "inner join c.empleadoAprobador ea " +
            "inner join c.empleadoCambio ec " +
            "inner join c.empleadoSolicitante es "
    )
    List<CambioHorarioPersistenciaDTO> findCambiodeHorario();

    //este es el que hay que cambiar
    @Query("select new com.example.practicas.models.dto.CambioHorarioConsultaDTO(c.idCambioHorario, es.idEmpleado, es.nombre, ea.idEmpleado, ea.nombre, ec.idEmpleado, ec.nombre, c.estado, c.descripcion, c.fechaSolicitud) " +
            "from CambioHorario c " +
            "left join c.empleadoAprobador ea " +
            "left join c.empleadoCambio ec " +
            "left join c.empleadoSolicitante es " +
            "where c.idCambioHorario = ?1"
    )
    Optional<CambioHorarioConsultaDTO> findCambiodeHorarioPorId(Long id);

    @Query("select new com.example.practicas.models.dto.CambioHorarioConsultaDTO(c.idCambioHorario, es.idEmpleado, es.nombre, ea.idEmpleado, ea.nombre, ec.idEmpleado, ec.nombre, c.estado, c.descripcion, c.fechaSolicitud) " +
            "from CambioHorario c " +
            "left join c.empleadoAprobador ea " +
            "left join c.empleadoCambio ec " +
            "left join c.empleadoSolicitante es " +
            " order by c.fechaSolicitud desc")
    Page<CambioHorarioConsultaDTO> findCambiodeHorarioForNamesEmployee(Pageable pageable);

    @Query("select new com.example.practicas.models.dto.CambioHorarioConsultaDTO(c.idCambioHorario, es.idEmpleado, es.nombre, ea.idEmpleado, ea.nombre, ec.idEmpleado, ec.nombre, c.estado, c.descripcion, c.fechaSolicitud) " +
            "from CambioHorario c " +
            "left join c.empleadoAprobador ea " +
            "left join c.empleadoCambio ec " +
            "left join c.empleadoSolicitante es " +
            "where es.idEmpleado = ?1" +
            " order by c.fechaSolicitud desc"
    )
    Page<CambioHorarioConsultaDTO> findCambiodeHorarioForNamesEmployeeForId(Long id, Pageable pageable);


}
