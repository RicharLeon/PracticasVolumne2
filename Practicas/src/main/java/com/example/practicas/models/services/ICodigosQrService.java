package com.example.practicas.models.services;

import com.example.practicas.models.dto.CodigosQrConsultaDTO;
import com.example.practicas.models.entity.CodigosQr;

import java.util.List;

public interface ICodigosQrService {


    List<CodigosQrConsultaDTO> mostrarQrPorEmpleado(Long id);

    CodigosQrConsultaDTO mostrarQrPorEmpleadoActivo(Long id);

    CodigosQr save(CodigosQr codigosQr);

    void inactivarCodigosQR(Long idEmpleado);
}
