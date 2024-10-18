package com.example.practicas.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CAMBIO_HORARIO")
public class CambioHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMBIO")
    private Long idCambioHorario;

    @Column(name = "ID_EMPLEADO_SOLICITANTE")
    private Long idEmpleadoSolicitante;

    @Column(name = "ID_EMPLEADO_APROBADOR")
    private Long idEmpleadoAprobador;

    @Column(name = "ID_EMPLEADO_CAMBIO")
    private Long idEmpleadoCambio;

    @Column(name = "DIA_CAMBIO")
    private String diaCambio;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA_SOLICITUD")
    private LocalDate fechaSolicitud;

    @Column(name = "ACEPTADO")
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO_SOLICITANTE", insertable = false, updatable = false)
    @JsonIgnore
    private Empleado empleadoSolicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO_APROBADOR", insertable = false, updatable = false)
    @JsonIgnore
    private Empleado empleadoAprobador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO_CAMBIO", insertable = false, updatable = false)
    @JsonIgnore
    private Empleado empleadoCambio;

    @PrePersist
    private void prePersist(){
        this.fechaSolicitud = LocalDate.now();
    }





}
