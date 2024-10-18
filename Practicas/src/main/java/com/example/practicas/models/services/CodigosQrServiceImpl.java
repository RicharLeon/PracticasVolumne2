package com.example.practicas.models.services;

import com.example.practicas.exceptions.NoContentException;
import com.example.practicas.models.dao.ICodigosQrDao;
import com.example.practicas.models.dao.IEmpleadoDao;
import com.example.practicas.models.dto.CodigosQrConsultaDTO;
import com.example.practicas.models.entity.CodigosQr;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CodigosQrServiceImpl implements ICodigosQrService {

    private final ICodigosQrDao codigosQrDao;
    private final IEmpleadoDao empleadoDao;


    @Override
    public List<CodigosQrConsultaDTO> mostrarQrPorEmpleado(Long id) {
         empleadoDao.findById(id)
                 .orElseThrow(() -> new NoContentException(MessageFormat
                 .format("El empleado {0} no se ha encontrado.", id)));
         return codigosQrDao.consultaQrEmpleado(id);
    }

    @Override
    public CodigosQrConsultaDTO mostrarQrPorEmpleadoActivo(Long id) {
        return codigosQrDao.consultaQrEmpleadoActivo(id)
                .orElseThrow(() -> new NoContentException(MessageFormat
                        .format("El empleado {0} no se ha encontrado.", id)));
    }


    @Override
    public CodigosQr save(CodigosQr codigosQr) {
        return codigosQrDao.save(codigosQr);
    }

    @Transactional
    @Override
    public void inactivarCodigosQR(Long idEmpleado){
        codigosQrDao.findByIdEmpleadoAndEstado(idEmpleado, true)
                .stream()
                .map( cq -> {
                    cq.setEstado(false);
                    return cq;
                }).forEach( codigosQrDao :: save);
    }
}
