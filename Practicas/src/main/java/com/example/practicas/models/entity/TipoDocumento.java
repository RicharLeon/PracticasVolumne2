package com.example.practicas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIPO_DOCUMENTO")
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_DOCUMENTO")
    public Long idTipoDocumento;

    @Column(name = "NOMBRE")
    public String nombre;
}
