package com.example.practicas.exceptions;

public class MyDataAccessException extends RuntimeException{

    public MyDataAccessException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }


    //PAra manejar el mensaje de error
    public static String buildErrorMessage(Exception e){
        return e.getMessage().concat(": ").concat(getMostSpecificCauseMessage(e));
    }

    public static String getMostSpecificCauseMessage(Throwable throwable){
        Throwable mostSpecificCause = throwable;
        while (mostSpecificCause.getCause() != null){
            mostSpecificCause = mostSpecificCause.getCause();
        }
        return mostSpecificCause.getMessage();
    }
}
