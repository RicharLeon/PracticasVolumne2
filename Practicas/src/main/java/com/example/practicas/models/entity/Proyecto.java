package com.example.practicas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROYECTOS")
@Data
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_proyecto")
    public Long idProyecto;
    @Column(name = "Nombre")
    public String Nombre;
    @Column(name = "Fecha_inicio")
    public LocalDate fechaInicio;
    @Column(name = "Fecha_fin")
    public LocalDate fechaFin;
    @Column(name = "Estado")
    public String estado;
    @Column(name = "DESCRIPCION")
    public String descripcionProyecto;


}
