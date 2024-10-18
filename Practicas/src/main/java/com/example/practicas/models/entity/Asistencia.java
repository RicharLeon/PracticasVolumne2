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
@Table(name = "ASISTENCIA")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASISTENCIA")
    private Long idAsistencia;

    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;

    @Column(name = "ASISTENCIA")
    private boolean asistencia;

    @Column(name = "ID_CODIGOS_QR")
    private Long idCodigoQR;

    @Column(name = "FECHAR")
    private LocalDate fechaAsistencia;

    // llaves
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CODIGOS_QR", insertable = false, updatable = false)
    private CodigosQr codigoQR;

    @PrePersist
    private void prePersist(){
        this.fechaAsistencia = LocalDate.now();
    }



}
