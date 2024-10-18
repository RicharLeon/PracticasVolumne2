package com.example.practicas.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpleadoConsultaByIdDTO {
    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String documento;
    private String nombreCargo;
    private String nombreContrato;
    private String nombreArea;
    private String nombreProyectos; // todos los proyectos que estan en la tabla proyectosporempelado, pero ahi solo esta el id, el nombre del proyecto y la descripcion del mismo entan en la tabla proyectos
    private String descripcionProyecto;
}
