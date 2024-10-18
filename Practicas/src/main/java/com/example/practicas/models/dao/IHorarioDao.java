package com.example.practicas.models.dao;

import com.example.practicas.models.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHorarioDao extends JpaRepository<Horario, Long> {
}
