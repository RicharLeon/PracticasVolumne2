package com.example.practicas.models.services;

import com.example.practicas.models.dao.ICodigosQrDao;
import com.example.practicas.models.dto.EmpleadoConsultaDTO;
import com.example.practicas.models.entity.CodigosQr;
import com.example.practicas.utils.GeneradorCodigosQr;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GeneracionQrTareaAutomatica {
    private final ICodigosQrService codigosQrService;
    private final IEmpleadoService empleadoService;
    private final ICodigosQrDao codigosQrDao;

    @Scheduled(cron = "0 0 2 ? * SUN") //   "0/1 * * * * ?" - cada minuto -- // "0 0 2 ? * SUN" -- CADA domingo a las dos de la mañana
    public void generacionQrPorEmpleado(){
        List<EmpleadoConsultaDTO> empleados = empleadoService.findAll();

        for (EmpleadoConsultaDTO empleado: empleados){
            String codigoQrDatos = generadorDataCodigosQr(empleado.getIdEmpleado());
            String codigoQr = GeneradorCodigosQr.GeneradorQr(codigoQrDatos);


            codigosQrService.inactivarCodigosQR(empleado.getIdEmpleado());
            CodigosQr codigosQrDTO = new CodigosQr();
            codigosQrDTO.setIdEmpleado(empleado.getIdEmpleado());
            codigosQrDTO.setCogigoQR(codigoQr);
            codigosQrDTO.setEstado(true);
            codigosQrService.save(codigosQrDTO);

            // se necesita implementar, que cuando se cree un nuevo codigo qr, el otro quede ene stado inactivo, ademas si se consume dos veces de igual manera
        }

    }



    private String generadorDataCodigosQr(Long idEmpleado){
        return "http://localhost:8081/api/consumir-qr/" + idEmpleado;
    }

}
