package com.example.practicas.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorException extends RuntimeException {

    private String mensaje;
    public ErrorException(String mensaje, Throwable traza) {
        super(mensaje, traza);
       // this.mensaje = mensaje;
    }

}
