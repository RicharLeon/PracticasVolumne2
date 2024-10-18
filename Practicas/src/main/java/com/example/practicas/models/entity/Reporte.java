package com.example.practicas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REPORTES")
public class Reporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REPORTE")
    private Long idReporte;
    @Column(name = "FECHA")
    private LocalDate fecha;
    @Column(name = "ID_ASISTENCIA")
    private Long idAsistencia;
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;

    // llaves
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ASISTENCIA", insertable = false, updatable = false)
    private Asistencia asistencia;

    @PrePersist
    private void prePersist() {
        this.fecha = LocalDate.now();
    }


}
