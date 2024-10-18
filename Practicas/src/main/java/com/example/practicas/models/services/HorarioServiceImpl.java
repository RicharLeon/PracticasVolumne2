package com.example.practicas.models.services;

import com.example.practicas.models.dao.IHorarioDao;
import com.example.practicas.models.entity.Horario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class HorarioServiceImpl implements IHorarioService {

    private final IHorarioDao horarioDao;
    @Override
    @Transactional
    public Horario saveHorario(Horario horario) {
        return horarioDao.save(horario);
    }
}
