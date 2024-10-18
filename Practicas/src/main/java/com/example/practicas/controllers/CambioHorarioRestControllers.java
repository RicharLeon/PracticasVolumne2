package com.example.practicas.controllers;

import com.example.practicas.exceptions.ErrorException;
import com.example.practicas.exceptions.MyDataAccessException;
import com.example.practicas.models.entity.CambioHorario;
import com.example.practicas.models.services.ICambioHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController

@RequestMapping("/api/cambio-horario")
public class CambioHorarioRestControllers {
    Map<String, Object> response = new HashMap<>();

    @Autowired
    private ICambioHorarioService cambioHorarioService;

    @GetMapping("/empleados")
    public ResponseEntity<?>todasLasSolicitudes(Pageable pageable){
        try {
            return new ResponseEntity<>(cambioHorarioService.findAll(pageable), HttpStatus.OK);
        }catch (DataAccessException e){
            throw new ErrorException("Error al realizar la consulta en la base de datos", e);
        }
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<?>porIdDeSolicitud(@PathVariable Long id, Pageable pageable){
        try{
            return new ResponseEntity<>(cambioHorarioService.findByNameAndId(id, pageable), HttpStatus.OK);
        }catch (DataAccessException e){
            throw new ErrorException("Error al realizar la consulta en la base de datos", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>porIdDeEmpelado(@PathVariable Long id){
        try{
            return new ResponseEntity<>(cambioHorarioService.findById(id), HttpStatus.OK);
        }catch (DataAccessException e){
            throw new ErrorException("Error al realizar la consulta en la base de datos", e);
        }
    }


    @PostMapping("")
    public ResponseEntity<?>nuevaSolicitudCambioHorario(@RequestBody CambioHorario cambioHorario){
        try {
            CambioHorario nuevoCambioHorario = cambioHorarioService.save(cambioHorario);
            response.put("mensaje", "Se registro el nuevo cambio de horario con exito");
            response.put("asistencia", nuevoCambioHorario);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (DataAccessException e){
            throw new RuntimeException("Error al realizar el Insert en la base de datos", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>editarCambioHorario(@PathVariable Long id, @RequestBody CambioHorario nuevoCambioHorario){
        try {
            CambioHorario cambioHorario = cambioHorarioService.update(nuevoCambioHorario, id);
            response.put("mensaje", "Se edito el registro con exito");
            response.put("asistencia", cambioHorario);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }catch (DataAccessException e){
            throw new MyDataAccessException("Error al realizar el update en la base de datos", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminarCambioHorario(@PathVariable Long id){
        try {
            cambioHorarioService.delete(id);
            response.put("mensaje", "Se elimino el cambio de horario con exito");
            response.put("asistencia", id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            throw new ErrorException("Error al eliminar cambio de horario", e);
        }
    }

}
