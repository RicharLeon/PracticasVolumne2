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
public class CodigosQrConsultaDTO {

    private Long idEmpleado;
    private String nombreEmpleado;
    private String cogigoQR;
    private LocalDate fecha;
    private boolean estado;
    private Integer cantidad_consumida;
}
