package com.example.practicas.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {
    /** Locale español * */
    private static final String LOCALE_ES = "es";
    /** Locale Colombia * */
    private static final String LOCALE_CO = "CO";
    private static final Locale COLOMBIA_LOCALE = new Locale(LOCALE_ES, LOCALE_CO);
    private DateUtil() {
        throw new IllegalStateException("Clase de DateUtil");
    }

    /**
     * Obtiene un String con la fecha formateada para caso de LocalDateTime
     *
     * @param formato
     * @return
     */
    public static String obtenerFechaSistemaColombia(LocalDate fecha, String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato, COLOMBIA_LOCALE);
        return formatter.format(fecha);
    }

}
