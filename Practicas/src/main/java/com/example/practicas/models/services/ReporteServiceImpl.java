package com.example.practicas.models.services;

import com.example.practicas.models.dao.IReporteDao;
import com.example.practicas.models.dto.ReporteConsultaDTO;
import com.example.practicas.models.entity.Reporte;
import com.example.practicas.utils.DateUtil;
import com.example.practicas.utils.LocalDAteDataResource;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReporteServiceImpl implements IReporteService{

    private final IReporteDao reporteDao;

    @Override
    public Reporte save(Reporte reporte) {
        return reporteDao.save(reporte);
    }

    @Override
    public byte[] reportePorUnEmpleado(Long idEmpleado) throws IOException {
        List<ReporteConsultaDTO> reporteConsultaDTO = reporteDao.reporteFindEmpleadosForIdDTO(idEmpleado);
        return generarReporte(reporteConsultaDTO, "reportePorEmpleado.xlsx");

    }

    @Override
    public byte[] reporteTodosLosEmpleados() throws IOException {
        List<ReporteConsultaDTO> reporteConsultaDTOS = reporteDao.reporteFindEmpleadosDTO();
        return generarReporte(reporteConsultaDTOS, "reporteTodosLosEmpleados.xlsx");
    }

    @Override
    public List<ReporteConsultaDTO> allReports() {
        return reporteDao.reporteFindEmpleadosDTO();
    }

    public byte[] generarReporte(List<ReporteConsultaDTO> reportes, String nombreArchivo) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Reporte reportex = new Reporte();

            Sheet sheet = workbook.createSheet("Reporte");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID Empleado");
            headerRow.createCell(1).setCellValue("Nombre Empleado");
            headerRow.createCell(2).setCellValue("Area");
            headerRow.createCell(3).setCellValue("Proyecto");
            headerRow.createCell(4).setCellValue("Asistencia");
            headerRow.createCell(5).setCellValue("Fecha");

            int rowNum = 1;

            for (ReporteConsultaDTO reporte : reportes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(reporte.getIdEmpleado());
                row.createCell(1).setCellValue(reporte.getNombre());
                row.createCell(2).setCellValue(reporte.getNombreArea());
                row.createCell(3).setCellValue(reporte.getNombreProyecto());
                row.createCell(4).setCellValue(reporte.isAsistencia());
                row.createCell(5).setCellValue(DateUtil.obtenerFechaSistemaColombia(reporte.getFechaAsistencia(),"dd/MM/yyyy"));
            }

            //Verificar que si funcione
            if(reportes.size()==1){
                ReporteConsultaDTO empleado = reportes.get(0);
                reportex.setIdEmpleado(empleado.getIdEmpleado());
            }
            save(reportex);

            File tempFile = File.createTempFile("temp", ".xlsx");
            try(FileOutputStream fileOut = new FileOutputStream(tempFile)){
                workbook.write(fileOut);
            }
            byte[] archivo = Files.readAllBytes(tempFile.toPath());
            tempFile.delete();
            return archivo;

        }catch (IOException exception){
            throw exception;
        }
    }

    @Override
    public byte[] generarReporteJasper(List<ReporteConsultaDTO> reporte) {
        try {
            // Cargar el archivo JasperReport
            InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/reportePDF.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

            // Compilar el informe JasperReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new LocalDAteDataResource(reporte));

            // Exportar el informe a bytes
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generarReporteJasperPorIdEmployee(Long empleadoID) {
        try {

            List<ReporteConsultaDTO> reporte = reporteDao.reporteFindEmpleadosForIdDTO(empleadoID);
            return generarReporteJasper(reporte);

        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }


}
