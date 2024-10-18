package com.example.practicas.models.dao;

import com.example.practicas.models.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICargoDao extends JpaRepository<Cargo, Long> {
}
