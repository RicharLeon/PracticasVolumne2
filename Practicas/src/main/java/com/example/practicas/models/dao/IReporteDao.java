package com.example.practicas.models.dao;

import com.example.practicas.models.dto.ReporteConsultaDTO;
import com.example.practicas.models.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReporteDao extends JpaRepository<Reporte, Long> {
    @Query("select new com.example.practicas.models.dto.ReporteConsultaDTO(r.idEmpleado, ie.nombre, are.nombre, py.Nombre, r.asistencia, r.fechaAsistencia) from Asistencia r " +
            "inner join r.empleado ie " +
            "inner join ie.area are " +
            "inner join ie.proyecto py "
    )
    List<ReporteConsultaDTO> reporteFindEmpleadosDTO();

    @Query("select new com.example.practicas.models.dto.ReporteConsultaDTO(r.idEmpleado, ie.nombre, are.nombre, py.Nombre, r.asistencia, r.fechaAsistencia) from Asistencia r " +
            "inner join r.empleado ie " +
            "inner join ie.area are " +
            "inner join ie.proyecto py " +
            "where ie.idEmpleado = ?1"
    )
    List<ReporteConsultaDTO> reporteFindEmpleadosForIdDTO(Long id);

}
