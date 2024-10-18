package com.example.practicas.models.services;

import com.example.practicas.models.dao.IParametroSistemaDao;
import com.example.practicas.models.entity.ParametroSistema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ParametroSistemaServiceImpl implements IParametroSistemaService{

    private final IParametroSistemaDao parametroDao;
    @Override
    public Optional<ParametroSistema> findPorValor(String valor) {
        return parametroDao.findByNombre(valor);
    }
}
