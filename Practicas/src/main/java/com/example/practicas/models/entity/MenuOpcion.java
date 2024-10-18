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
@Table(name = "MENUOPCION")
public class MenuOpcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENUOPCION_ID")
    private Long idMenuOpcion;

    @Column(name = "MENUOPCION_NOMBRE")
    public String nombreMenuOpcion;
    @Column(name = "MENUOPCION_DESCRIPCION")
    public String descripcionMenuOpcion;
    @Column(name = "MENUOPCION_ICONO")
    public String iconoMenuOpcion;
    @Column(name = "MENUOPCION_RUTA")
    public String rutaMenuOpcion;
    @Column(name = "MENUOPCION_ESTADO")
    public String estadoMenuOpcion;
    @Column(name = "MENUOPCION_MENUOPCION_ID")
    public Long idPadreMenuOpcion;
    @Column(name = "MENUOPCION_ORDEN")
    public Long ordenMenuOpcion;
    @Column(name = "MENUOPCION_CLAVE")
    public String claveMenuOpcion;
    @Column(name = "MENUOPCION_FECHACREA")
    public LocalDate fechaCreacion;
    @Column(name = "MENUOPCION_USUARIOCREA")
    public String usuarioCreador;
    @Column(name = "MENUOPCION_FECHAMODIF")
    public LocalDate fechaModificacion;
    @Column(name = "MENUOPCION_USUARIOMODIF")
    public String usuarioModificador;

    @PrePersist
    private void prePersist(){
        this.fechaCreacion = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDate.now();
    }
}
