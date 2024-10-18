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
@Table(name = "PARAMETRO_SISTEMA")
public class ParametroSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PARAMETRO")
    private Long idParametro;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "VALOR")
    private String valor;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CREATE_AT")
    private LocalDate fechaCreacion;
    @Column(name = "UPDATE_AT")
    private LocalDate fechaActualizacion;
    @PrePersist
    private void prePersist(){
        this.fechaCreacion = LocalDate.now();
    }

}
