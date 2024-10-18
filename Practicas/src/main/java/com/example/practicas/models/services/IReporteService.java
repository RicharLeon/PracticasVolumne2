package com.example.practicas.models.services;

import com.example.practicas.models.dto.ReporteConsultaDTO;
import com.example.practicas.models.entity.Reporte;

import java.io.IOException;
import java.util.List;

public interface IReporteService {
    Reporte save(Reporte reporte);
    byte[] reportePorUnEmpleado(Long idEmpleado) throws IOException;
    byte[] reporteTodosLosEmpleados() throws IOException;

    List<ReporteConsultaDTO> allReports();

    byte[] generarReporteJasper(List<ReporteConsultaDTO> reporte);
    byte[] generarReporteJasperPorIdEmployee(Long empleadoID);

}
