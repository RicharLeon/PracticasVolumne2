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
@Table(name = "PROYECTOS_EMPLEADO")
public class ProyectosPorEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROYECTOS_EMPLEADO")
    private Long idProyectosEmpleado;

    @Column(name = "ID_EMPLEADO")
    public Long idEmpleado;

    @Column(name = "ID_PROYECTO")
    public Long idProyecto;

    @Column(name = "ESTADO_EMPL_PROYECT")
    public boolean estadoEmpleadoEnProeycto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROYECTO", insertable = false, updatable = false)
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Empleado empleado;
}
