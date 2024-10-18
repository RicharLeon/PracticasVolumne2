package com.example.practicas.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaDTO {

    private Short codigo;
    private String mensaje;
    private String traza;
    private List<String> erroresCampos;

}
