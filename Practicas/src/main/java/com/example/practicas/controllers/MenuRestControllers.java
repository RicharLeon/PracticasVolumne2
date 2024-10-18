package com.example.practicas.controllers;

import com.example.practicas.models.dto.MenuConsultaDTO;
import com.example.practicas.models.services.IMenuGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/menu")
public class MenuRestControllers {

    @Autowired
    private IMenuGrupoService menuGrupoService;

    @GetMapping("/{grupo}")
    public ResponseEntity<List<MenuConsultaDTO>> index(@PathVariable String grupo) {
        return ResponseEntity.ok(menuGrupoService.menuByGrupo(grupo));
    }
}
