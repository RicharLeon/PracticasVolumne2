package com.example.practicas.controllers;

import com.example.practicas.exceptions.MyDataAccessException;
import com.example.practicas.models.entity.Asistencia;
import com.example.practicas.models.services.IAsistenciaService;
import com.example.practicas.utils.ControlCamposFaltantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController

@RequestMapping("/api/asistencia")
public class AsistenciaRestControllers {
    Map<String, Object> response = new HashMap<>();

    @Autowired
    private IAsistenciaService asistenciaService;

    @PostMapping("")
    public ResponseEntity<?>nuevaAsistencia(@Valid @RequestBody Asistencia asistenciaPersistenciaDTO, BindingResult bindingResult) {
        ControlCamposFaltantesUtil.validarPropiedades(bindingResult);

        try {
            Asistencia newAsistencia = asistenciaService.save(asistenciaPersistenciaDTO);
            response.put("mensaje", "Se registro la nueva asistencia con exito");
            response.put("asistencia", newAsistencia);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Error al realizar el registro de la asistencia en la base de datos", e);
        }
    }

}
