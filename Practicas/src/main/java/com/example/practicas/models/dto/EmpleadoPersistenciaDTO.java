package com.example.practicas.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmpleadoPersistenciaDTO {

    @NotEmpty(message = "El campo nombre es necesario")
    private String nombre;
    @NotEmpty(message = "El apellido es necesario")
    private String apellido;
    @NotEmpty(message = "El documento es necesario")
    private String documento;
    @NotNull(message = "El tipo de documento es necesario")
    private Long idTipoDocumento;
    @NotNull(message = "El tipo de documento es necesario")
    private Long idCargo;
    @NotNull(message = "El contrato es necesario")
    private Long idTipoContrato;
    @NotNull(message = "El area es necesario")
    private Long idArea;
    @NotNull(message = "El proyecto es necesario")
    private Long idProyecto;
}
