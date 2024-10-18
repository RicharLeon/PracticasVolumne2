package com.example.practicas.models.services;

import com.example.practicas.models.dto.CambioHorarioConsultaDTO;
import com.example.practicas.models.entity.CambioHorario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICambioHorarioService {
    Page<CambioHorarioConsultaDTO> findAll(Pageable pageable);

    Page<CambioHorarioConsultaDTO> findByNameAndId(Long id, Pageable pageable);
    Optional<CambioHorarioConsultaDTO> findById(Long id);
    CambioHorario save(CambioHorario cambioHorario);
    void delete(Long id);
    CambioHorario update (CambioHorario cambioHorarioPersistenciaDTO, Long id);
}
