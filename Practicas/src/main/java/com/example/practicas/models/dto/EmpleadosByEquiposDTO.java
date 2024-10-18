package com.example.practicas.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpleadosByEquiposDTO {

    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private Long idTipoDocumento;
    private String documento;
    private Long idCargo;
    private Long idTipoContrato;
    private Long idArea;
    private Long idProyecto;
    private Long idEquipo;
}
