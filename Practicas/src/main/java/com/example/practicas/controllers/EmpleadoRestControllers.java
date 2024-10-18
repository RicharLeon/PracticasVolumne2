package com.example.practicas.controllers;

import com.example.practicas.exceptions.ErrorException;
import com.example.practicas.exceptions.MyDataAccessException;
import com.example.practicas.models.dto.EmpleadoConsultaByIdDTO;
import com.example.practicas.models.dto.EmpleadoConsultaDTO;
import com.example.practicas.models.dto.EmpleadoPersistenciaDTO;
import com.example.practicas.models.entity.Empleado;
import com.example.practicas.models.services.IEmpleadoService;
import com.example.practicas.utils.ControlCamposFaltantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestControllers {

    Map<String, Object> response = new HashMap<>();

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/empleados")
    public ResponseEntity<List<EmpleadoConsultaDTO>> index() {
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/empleado/proyectos/{id}")
    public ResponseEntity<?>proyectsForEmployee(@PathVariable Long id){
        return ResponseEntity.ok(empleadoService.proyectsForEmployee(id));
    }

    @GetMapping("/empleado/equipos/{id}")
    public ResponseEntity<?>EquiposById(@PathVariable Long id){
        return ResponseEntity.ok(empleadoService.EquiposById(id));
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> unSoloEmpleado(@PathVariable Long id) {
        try {
            return new ResponseEntity<EmpleadoConsultaDTO>(empleadoService.findById(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ErrorException("Error al realizar la consulta en la base de datos", e);
        }
    }

    @GetMapping("/empleado/data/{id}")
    public ResponseEntity<List<EmpleadoConsultaByIdDTO>> unSoloEmpleadoAllData(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.findByIdAll(id));

    }


    @PostMapping("/empleado")
    public ResponseEntity<?> agregarNuevoEmpelado( @Valid @RequestBody EmpleadoPersistenciaDTO empleado, BindingResult bindingResult) {

        ControlCamposFaltantesUtil.validarPropiedades(bindingResult);

        try {
            Empleado empleadoNuevo = empleadoService.save(empleado);
            response.put("mensaje", "El empleado ha sido creado con exito");
            response.put("empleado", empleadoNuevo);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Error al realizar el insert en la base de datos", e);
        }

    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> editarEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {
        try {
            empleadoService.update(empleado, id);
            response.put("mensaje", "El empleado ah sido actualizado con exito");
            response.put("empleado", empleado);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Error al editar el empleado", e);
        }
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        try {
            empleadoService.delete(id);
            response.put("mensaje", "El empleado ah sido eliminado con exito");
            response.put("empleado", id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ErrorException("Error al eliminar el user", e);
        }
    }
}