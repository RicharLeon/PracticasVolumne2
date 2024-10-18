package com.example.practicas.models.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLEADOS")
@Getter
@Setter
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "DOCUMENTO", unique = true)
    private String documento;

    // cambio nombre ID
    @Column(name = "ID_TIPO_DOCUMENTO")
    private Long idTipoDocumento;

    // Crear representación de la relación
    @Column(name = "ID_CARGO")
    private Long idCargo;

    @Column(name = "ID_TIPO_CONTRATO")
    private Long idTipoContrato;

    @Column(name = "ID_AREA")
    private Long idArea;

    @Column(name = "ID_PROYECTO")
    private Long idProyecto;

    @Column(name = "EQUIPO_ID")
    private Long idEquipos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", insertable = false, updatable = false)
    private TipoDocumento tipoDocumento;


    // Crear representación de la relación
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARGO", insertable = false, updatable = false)
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CONTRATO", insertable = false, updatable = false)
    private TipoContrato tipoContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AREA", insertable = false, updatable = false)
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROYECTO", insertable = false, updatable = false)
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EQUIPO_ID", insertable = false, updatable = false)
    private Equipos equipos;

}
