package com.example.practicas.models.services;

import com.example.practicas.models.entity.ParametroSistema;

import java.util.Optional;

public interface IParametroSistemaService {

    Optional<ParametroSistema> findPorValor(String valor);

}
