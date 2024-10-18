package com.example.practicas.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuConsultaDTO {

    private Long idMenuOpcion;
    public String nombreMenuOpcion;
    public String descripcionMenuOpcion;
    public String iconoMenuOpcion;
    public String rutaMenuOpcion;
    public String estadoMenuOpcion;
    public Long idPadreMenuOpcion;
    public Long ordenMenuOpcion;
    public String claveMenuOpcion;
    private Long idMenuGrupo;
    public String nombreMenuGrupo;
    public String estadoMenuGrupo;
}
