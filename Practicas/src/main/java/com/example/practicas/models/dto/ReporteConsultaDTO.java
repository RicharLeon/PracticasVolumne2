package com.example.practicas.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteConsultaDTO {

    private Long idEmpleado;
    private String nombre;
    private String nombreArea;
    private String nombreProyecto;
    private boolean asistencia;
    private LocalDate fechaAsistencia;

}
