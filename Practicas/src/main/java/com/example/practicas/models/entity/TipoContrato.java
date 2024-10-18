package com.example.practicas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TIPO_CONTRATO")
@Data
public class TipoContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_tipo_contrato")
    public Long tipoContrato;

    @Column(name = "Nombre")
    public String nombre;

}
