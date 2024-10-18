package com.example.practicas.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpleadoConsultaDTO {

    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String documento;
    private String nombreCargo;
    private String nombreContrato;
    private String nombreArea;
    private String nombreProyecto;

}
