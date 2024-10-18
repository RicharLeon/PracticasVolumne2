package com.example.practicas.utils;

import com.example.practicas.models.dao.IReporteDao;
import com.example.practicas.models.dto.ReporteConsultaDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;



public class GenerarReportes {

    private final IReporteDao reporteDao;

    public GenerarReportes(IReporteDao reporteDao) {
        this.reporteDao = reporteDao;
    }

    public byte[] generateReport(String reportType, Map<String, Object> parameters) throws JRException {
        try {
            InputStream reportFile = new ClassPathResource("reportes/reportePDF.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reporteDao.reporteFindEmpleadosDTO());
            // Generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            if (reportType.equalsIgnoreCase("pdf")) {
                return exportToPdf(jasperPrint);
            } else if (reportType.equalsIgnoreCase("excel")) {
                return exportToExcel(jasperPrint);
            } else {
                throw new IllegalArgumentException("Formato de informe no es valido: "+ reportType);
            }

        }catch (Exception e){
            throw new JRException("Error al generar el informe");
        }
    }

    private byte[] exportToPdf(JasperPrint jasperPrint) throws JRException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream.toByteArray();
    }

    private byte[] exportToExcel(JasperPrint jasperPrint) throws JRException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        return outputStream.toByteArray();
    }

    private byte[] generarReporteJasper(List<ReporteConsultaDTO> reporte) {
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


}
