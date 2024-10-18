package com.example.practicas.configuracion;

import com.example.practicas.exceptions.CamposException;
import com.example.practicas.exceptions.ErrorException;
import com.example.practicas.exceptions.MyDataAccessException;
import com.example.practicas.exceptions.NoContentException;
import com.example.practicas.models.dto.RespuestaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class CommonControllerAdvice  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Object> handleNoContentException(NoContentException e) {
        log.error(e.getMessage());
        RespuestaDTO respuesta = RespuestaDTO.builder().
                mensaje(e.getMessage())
                .codigo((short) 1)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Object> handleErrorException(ErrorException e) {
        log.error(e.getMessage());
        RespuestaDTO respuesta = RespuestaDTO.builder()
                .mensaje(e.getMensaje())
                .codigo((short) 2)
                .traza(MyDataAccessException.buildErrorMessage(e))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @ExceptionHandler(MyDataAccessException.class)
    public ResponseEntity<Object> handleMyDataAccessException(DataAccessException e){
        log.error(e.getMessage());
        RespuestaDTO respuestaDTO = RespuestaDTO.builder()
                .mensaje(e.getMessage())
                .codigo((short) 3)
                .traza(MyDataAccessException.buildErrorMessage(e))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaDTO);

    }

    @ExceptionHandler(CamposException.class)
    public ResponseEntity<Object> handleMyDataAccessException(CamposException e){
        log.error(e.getMessage());
        RespuestaDTO respuestaDTO = RespuestaDTO.builder()
                .mensaje(e.getMessage())
                .codigo((short) 4)
                .traza(MyDataAccessException.buildErrorMessage(e))
                .erroresCampos(e.getErroresCampos())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaDTO);

    }

}
