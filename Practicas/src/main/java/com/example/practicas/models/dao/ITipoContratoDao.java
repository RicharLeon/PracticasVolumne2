package com.example.practicas.models.dao;

import com.example.practicas.models.entity.TipoContrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoContratoDao extends JpaRepository<TipoContrato, Long> {
}
