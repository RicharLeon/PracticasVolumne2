package com.example.practicas.models.dao;

import com.example.practicas.models.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoDocumentoDao extends JpaRepository<TipoDocumento, Long> {
}
