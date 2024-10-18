package com.example.practicas.models.services;

import com.example.practicas.models.dao.IAsistenciaDao;
import com.example.practicas.models.entity.Asistencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements IAsistenciaService{


    private final IAsistenciaDao asistenciaDao;


    @Override
    @Transactional
    public Asistencia save(Asistencia asistenciaPersistenciaDTO) {
        //Asistencia asistenciaEntity = AsistenciaMapper.INSTANCIA.dtoPersistenciaToEntity(asistenciaPersistenciaDTO);
        return asistenciaDao.save(asistenciaPersistenciaDTO);
    }
}
