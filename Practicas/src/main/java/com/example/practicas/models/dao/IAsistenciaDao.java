package com.example.practicas.models.dao;

import com.example.practicas.models.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAsistenciaDao extends JpaRepository<Asistencia, Long> {
}
