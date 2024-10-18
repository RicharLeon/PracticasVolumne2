package com.example.practicas.models.services;

import com.example.practicas.models.dao.IMenuGrupoDao;
import com.example.practicas.models.dto.MenuConsultaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuGrupoServicesImpl implements IMenuGrupoService{
    private final IMenuGrupoDao menuGrupoDao;
    @Override
    public List<MenuConsultaDTO> menuByGrupo(String grupo) {
        return menuGrupoDao.findMenuByGrupo(grupo);
    }
}
