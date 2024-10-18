package com.example.practicas.mappers;

import com.example.practicas.models.dto.EmpleadoPersistenciaDTO;
import com.example.practicas.models.entity.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    EmpleadoMapper INSTANCIA = Mappers.getMapper(EmpleadoMapper.class);

    Empleado dtoPersistenciaToEntity(EmpleadoPersistenciaDTO empleado);

}
