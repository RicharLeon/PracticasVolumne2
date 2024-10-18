package com.example.practicas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HORARIO")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private Long idHorario;
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;
    @Column(name = "CANTIDAD_DIAS")
    private int cantidadDias;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "SEMANA")
    private int semana;
    @Column(name = "MES")
    private int mes;
    @Column(name = "DIAS")
    private String dias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Empleado empleado;
}
