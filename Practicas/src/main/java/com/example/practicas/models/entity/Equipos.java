package com.example.practicas.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EQUIPOS")
public class Equipos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EQUIPOS_ID")
    private Long idEquipos;

    @Column(name = "NOMBRE")
    public String nombre;
    @Column(name = "DESCRIPCION")
    public String descripcion;
    @Column(name = "ESTADO")
    public boolean estado;
    @Column(name = "CREATE_AT")
    public LocalDate create_ate;
    @PrePersist
    private void prePersist(){
        this.create_ate = LocalDate.now();
    }

}
