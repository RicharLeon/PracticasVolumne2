package com.example.practicas.models.services;

import com.example.practicas.models.dto.MenuConsultaDTO;

import java.util.List;

public interface IMenuGrupoService {

    List<MenuConsultaDTO> menuByGrupo(String grupo);
}
