package com.example.practicas.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CODIGOS_QR")
public class CodigosQr {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CODIGOS_QR")
    private Long idCodigoQR;
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;
    @Column(name = "CODIGOQR")
    private String cogigoQR;
    @Column(name = "FECHA")
    private LocalDate fecha;
    @Column(name = "ESTADO")
    private boolean estado;
    @Column(name = "cantidad_consumida")
    private int cantidad_consumida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", insertable = false, updatable = false)
    private Empleado empleado;

    @PrePersist
    private void prePersist(){
        this.fecha = LocalDate.now();
        this.cantidad_consumida=0;
    }
}
