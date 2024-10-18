package com.example.practicas.models.dao;

import com.example.practicas.models.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProyectoDao extends JpaRepository<Proyecto, Long> {


}
