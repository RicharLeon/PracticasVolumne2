package com.example.practicas.controllers;

import com.example.practicas.exceptions.ErrorException;
import com.example.practicas.models.dto.ReporteConsultaDTO;
import com.example.practicas.models.services.IReporteService;
import com.example.practicas.utils.GenerarReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@CrossOrigin(origins = {"*"})
@RestController

@RequestMapping("/api/reportes")
public class ReporteRestControllers {

    Map<String, Object> response = new HashMap<>();

    @Autowired
    private IReporteService reporteService;

    private static final Logger logger = Logger.getLogger(GenerarReportes.class.getName());


    @Secured("USER")
    @GetMapping("/probar")
    public ResponseEntity<?> probar() {
        return ResponseEntity.ok("hola");
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<?> generarInformePorEmpleado(@PathVariable Long idEmpleado) {
        try {
            byte[] archivo = reporteService.reportePorUnEmpleado(idEmpleado);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=temp.xlsx");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            ByteArrayResource resource = new ByteArrayResource(archivo);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace(); // Loguea la excepción para fines de depuración
            return ResponseEntity.status(500).body("Error al generar el informe: " + e.getMessage());
        }
    }

    @GetMapping("/empleados")
    public ResponseEntity<?> generarInformeTodosEmpleados(){
        try {
            byte[] archivo =  reporteService.reporteTodosLosEmpleados();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=temporal.xlsx");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            ByteArrayResource resource = new ByteArrayResource(archivo);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace(); // Loguea la excepción para fines de depuración
            return ResponseEntity.status(500).body("Error al generar el informe: " + e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?>todosLosReportes(){
        try{
            return new ResponseEntity<>(reporteService.allReports(), HttpStatus.OK);
        }catch (DataAccessException e){
            throw new ErrorException("Error al realizar la consulta en la base de datos", e);
        }
    }


    @GetMapping("/for-empleado/{idEmpleado}")
    public ResponseEntity<byte[]> generarReportePorEmpleado(@PathVariable Long idEmpleado){
        byte[] newReporte = reporteService.generarReporteJasperPorIdEmployee(idEmpleado);

        // Generar el nombre del archivo con la fecha y el ID del empleado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String formattedDate = dateFormat.format(new Date());
        String filename = "reporte-" + formattedDate + "-empleado-" + idEmpleado + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", filename);
        if (newReporte != null) {
            headers.setContentLength(newReporte.length);
        }
        return new ResponseEntity<>(newReporte, headers, HttpStatus.OK);

    }

    @GetMapping("/general")
    public ResponseEntity<byte[]> generarReporte() {
        List<ReporteConsultaDTO> reporte = reporteService.allReports();
        byte[] newReporte = reporteService.generarReporteJasper(reporte);
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "reporte-asistencia.pdf");
        if (newReporte != null) {
            headers.setContentLength(newReporte.length);
        }
        //headers.setContentLength(newReporte.length);

        return new ResponseEntity<>(newReporte, headers, HttpStatus.OK);
    }

}
