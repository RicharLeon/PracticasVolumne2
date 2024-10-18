package com.example.practicas.utils;

import com.example.practicas.models.dto.ReporteConsultaDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Iterator;
import java.util.List;

public class LocalDAteDataResource  implements JRDataSource {
    private Iterator<ReporteConsultaDTO> iterator;
    private ReporteConsultaDTO currentObject;

    public LocalDAteDataResource(List<ReporteConsultaDTO> data) {
        this.iterator = data.iterator();
    }

    @Override
    public boolean next() throws JRException {
        boolean hasNext = iterator.hasNext();
        if (hasNext) {
            currentObject = iterator.next();
        }
        return hasNext;
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
        if ("fechaAsistencia".equals(field.getName())) {
            return java.sql.Date.valueOf(currentObject.getFechaAsistencia());
        }
        // Resto de los campos
        switch (field.getName()) {
            case "idEmpleado":
                return currentObject.getIdEmpleado();
            case "nombre":
                return currentObject.getNombre();
            case "nombreArea":
                return currentObject.getNombreArea();
            case "nombreProyecto":
                return currentObject.getNombreProyecto();
            case "asistencia":
                return currentObject.isAsistencia();
            default:
                return null;
        }
    }
}
