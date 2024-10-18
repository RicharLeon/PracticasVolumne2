package com.example.practicas.utils;

import com.example.practicas.models.dto.EmpleadoConsultaDTO;
import com.example.practicas.models.entity.Horario;
import com.example.practicas.models.entity.ParametroSistema;
import com.example.practicas.models.services.IEmpleadoService;
import com.example.practicas.models.services.IHorarioService;
import com.example.practicas.models.services.IParametroSistemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class CreacionHorario {
    private final IEmpleadoService empleadoService;
    private final IHorarioService horarioService;
    private final IParametroSistemaService parametroService;

    @Scheduled(cron = "0 0 2 ? * SUN")
    public void nuevoHorario(){
        List<EmpleadoConsultaDTO> empleados = empleadoService.findAll();
        Optional<ParametroSistema> parametro =  parametroService.findPorValor(Constantes.CANTIDAD_PUESTOS);

        Integer cantidadDisponible = Integer.parseInt(parametro.get().getValor());
        List<String> diasSemana = new ArrayList<>();
        validarCantidadEmpleadosOficina2(diasSemana, cantidadDisponible);
        for (EmpleadoConsultaDTO empleado: empleados){
            Horario horario = new Horario();
            horario.setIdEmpleado(empleado.getIdEmpleado());
            horario.setCantidadDias(2);
            horario.setMes(LocalDate.now().getMonthValue());
            horario.setSemana(LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()));

            if (!validarCantidadEmpleadosOficina2(diasSemana, 2)){
                horario.setObservacion("No hay cupo disponible esta semana");
            }else {
                String result = diasRandomSemana(diasSemana, horario.getCantidadDias()).stream().collect(Collectors.joining(", "));
                horario.setDias(result);
            }

            horarioService.saveHorario(horario);
        }

    }

    public List<String> diasRandomSemana(List<String> dias, int cantidadDias){
        Random random = new Random();
        while (dias.size() < cantidadDias) {
            int diasRandom = random.nextInt(5) + 1;

            String dia;
            switch (diasRandom) {
                case 1:
                    dia = "Lunes";
                    break;
                case 2:
                    dia = "Martes";
                    break;
                case 3:
                    dia = "Miércoles";
                    break;
                case 4:
                    dia = "Jueves";
                    break;
                case 5:
                    dia = "Viernes";
                    break;
                default:
                    dia = "Día Desconocido";
            }

            if (!dias.contains(dia)) {
                dias.add(dia);
            }
        }
        return dias;
    }

    public boolean validarCantidadEmpleadosOficina(List<String> data, Integer cantidadDisponible){

        int contadorLunes= 0;
        int contadorMartes= 0;
        int contadorMiercoles= 0;
        int contadorJueves= 0;
        int contadorViernes= 0;

        for (String dia: data){
            if (dia.equals("Lunes")){
                contadorLunes ++;
            }else if (dia.equals("Martes")){
                contadorMartes ++;
            }else if (dia.equals("Miércoles")){
                contadorMiercoles ++;
            } else if (dia.equals("Jueves")) {
                contadorJueves ++;
            } else if (dia.equals("Viernes")) {
                contadorViernes ++;
            }
        }


        if (contadorLunes>cantidadDisponible || contadorMartes>cantidadDisponible || contadorMiercoles>cantidadDisponible || contadorJueves>cantidadDisponible || contadorViernes>cantidadDisponible){
            return false; // Indicar que no se cumple la condición
        }

        return true; // Indicar que se cumple la condición

    }

    public boolean validarCantidadEmpleadosOficina2(List<String> diasAsignados, int cantidadDisponible) {
        Map<String, Integer> contadorDias = new HashMap<>();

        for (String dia : diasAsignados) {
            contadorDias.put(dia, contadorDias.getOrDefault(dia, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : contadorDias.entrySet()) {
            String dia = entry.getKey();
            int cantidadAsignada = entry.getValue();

            if (cantidadAsignada > cantidadDisponible) {
                System.out.println("Demasiados empleados asignados el " + dia + ". Cantidad: " + cantidadAsignada);
                return false;
            }
        }

        return true;
    }
}
