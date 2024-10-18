package com.example.practicas.mappers;

import com.example.practicas.models.dto.AsistenciaPersistenciaDTO;
import com.example.practicas.models.entity.Asistencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper {

    AsistenciaMapper INSTANCIA = Mappers.getMapper(AsistenciaMapper.class);

    Asistencia dtoPersistenciaToEntity(AsistenciaPersistenciaDTO asistencia);
}
