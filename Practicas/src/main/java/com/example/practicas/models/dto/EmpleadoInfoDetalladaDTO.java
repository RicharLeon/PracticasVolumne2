package com.example.practicas.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmpleadoInfoDetalladaDTO {
    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private String documento;
    private List<ProyectosEmpleadoDTO> Proyectos; // todos los proyectos que estan en la tabla proyectosporempelado, pero ahi solo esta el id, el nombre del proyecto y la descripcion del mismo entan en la tabla proyectos

}
