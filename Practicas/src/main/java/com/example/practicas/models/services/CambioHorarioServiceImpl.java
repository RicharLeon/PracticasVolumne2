package com.example.practicas.models.services;

import com.example.practicas.exceptions.NoContentException;
import com.example.practicas.models.dao.ICambioHorarioDao;
import com.example.practicas.models.dto.CambioHorarioConsultaDTO;
import com.example.practicas.models.entity.CambioHorario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.text.MessageFormat;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CambioHorarioServiceImpl implements ICambioHorarioService{

    private final ICambioHorarioDao cambioHorarioDao;

    @Override
    public Page<CambioHorarioConsultaDTO> findAll(@PageableDefault(page = 0, size=10) Pageable pageable) {
        return cambioHorarioDao.findCambiodeHorarioForNamesEmployee(pageable);
    }

    @Override
    public Page<CambioHorarioConsultaDTO> findByNameAndId(Long id,Pageable pageable) {
        return cambioHorarioDao.findCambiodeHorarioForNamesEmployeeForId(id, pageable);
    }

    @Override
    public Optional<CambioHorarioConsultaDTO> findById(Long id) {
        return cambioHorarioDao.findCambiodeHorarioPorId(id);
    }

    @Transactional
    @Override
    public CambioHorario save(CambioHorario cambioHorario) {
        return cambioHorarioDao.save(cambioHorario);
    }

    @Override
    public void delete(Long id) {
        CambioHorario cambioHorarioRecibido = cambioHorarioDao.findById(id)
                .orElseThrow(()-> new NoContentException(MessageFormat
                        .format("Solicitud con id {0} a elimianr no existe", id)));
        cambioHorarioDao.deleteById(id);
    }

    @Override
    public CambioHorario update(CambioHorario cambioHorario, Long id) {
        CambioHorario cambioHorarioRecibido = cambioHorarioDao.findById(id)
                .orElseThrow(()-> new NoContentException(MessageFormat
                        .format("Solicitud con id {0} a editar no existe", id)));
        cambioHorarioRecibido.setEstado(cambioHorario.isEstado());
        cambioHorarioRecibido.setIdEmpleadoCambio(cambioHorario.getIdEmpleadoCambio());
        cambioHorarioRecibido.setIdEmpleadoAprobador(cambioHorario.getIdEmpleadoAprobador());
        cambioHorarioRecibido.setIdEmpleadoSolicitante(cambioHorario.getIdEmpleadoSolicitante());
        cambioHorarioRecibido.setDescripcion(cambioHorario.getDescripcion());

        return cambioHorarioDao.save(cambioHorarioRecibido);
    }
}
