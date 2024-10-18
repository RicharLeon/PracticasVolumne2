package com.example.practicas.controllers;

import com.example.practicas.exceptions.ErrorException;
import com.example.practicas.models.dto.CodigosQrConsultaDTO;
import com.example.practicas.models.services.ICodigosQrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController

@RequestMapping("/api")
public class CodigosQrControllers {
    Map<String, Object> response = new HashMap<>();

    @Autowired
    private ICodigosQrService codigosQrService;

    // Verificar todos los codigos qr
    /*
    @GetMapping("/codigoQr/{id}")
    public ResponseEntity<List<CodigosQrConsultaDTO>> codigoQRforEmpleado(@PathVariable Long id){
        try{
            return ResponseEntity.ok(codigosQrService.mostrarQrPorEmpleado(id));
        }catch (DataAccessException e){
            throw new ErrorException("Empleado con id : "+ id +" no existe", e);
        }
    }
    */


    // Verificar solo por un codigo qr
    @GetMapping("/codigoQr/{id}")
    public ResponseEntity<?> codigoQrforEmpleadoActivo(@PathVariable Long id){
        try {
            return new ResponseEntity<CodigosQrConsultaDTO>(codigosQrService.mostrarQrPorEmpleadoActivo(id), HttpStatus.OK);
        }catch (DataAccessException e){
            throw new ErrorException("Error al buscar la data", e);
        }
    }

    @GetMapping("/consumir-qr/{idQr}")
    public ResponseEntity<?> actualizarQr(@PathVariable Long idQr) {
        System.out.println("Richar esta muy viggaaaaaaaaaaaaaaa");
        try {
            // Puedes redirigir a la página de Angular con la URL deseada
            String urlAngular = "http://localhost:4200/menu/" + idQr;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", urlAngular);
            return new ResponseEntity<>(headers, HttpStatus.FOUND);  // HttpStatus.FOUND es el código 302
        } catch (DataAccessException e) {
            throw new ErrorException("Error al buscar la data", e);
        }
    }

}
