package com.example.practicas.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CambioHorarioConsultaDTO {

    private Long idCambioHorario;
    private Long idEmpleadoSolicitante;
    private String nombreEmpleadoSolicitante;
    private Long idEmpleadoAprobador;
    private String nombreEmpleadoAprobador;
    private Long idEmpleadoCambio;
    private String nombreEmpleadoCambio;

    private boolean estado;
    private String descripcion;
    private LocalDate fechaSolicitud;
}
