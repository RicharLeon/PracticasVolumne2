package com.example.practicas.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaPersistenciaDTO {

    private Long idEmpleado;
    @NotNull
    private boolean asistencia;
    @NotNull(message = "Se necesita id codigoQR")
    private Long idCodigoQR;
    private LocalDate fechaAsistencia;


}
