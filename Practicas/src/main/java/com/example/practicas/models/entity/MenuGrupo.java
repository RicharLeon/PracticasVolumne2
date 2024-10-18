package com.example.practicas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MENUGRUPO")
public class MenuGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENUGRUPO_ID")
    private Long idMenuGrupo;

    @Column(name = "MENUGRUPO_GRUPO_NOMBRE")
    public String nombreMenuGrupo;
    @Column(name = "MENUOPCION_ID")
    public Long idMenuOpcion;
    @Column(name = "MENUGRUPO_ESTADO")
    public String estadoMenuGrupo;
    @Column(name = "MENUGRUPO_FECHA_CREA")
    public LocalDate fechaCreacion;
    @Column(name = "MENUGRUPO_USUARIO_CREA")
    public Long idUsuarioCrea;
    @Column(name = "MENUGRUPO_FECHA_MODIF")
    public LocalDate fechaModificacion;
    @Column(name = "MENUGRUPO_USUARIO_MODIF")
    public String usuarioModifica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENUOPCION_ID", insertable = false, updatable = false)
    private MenuOpcion menuOpcion;

    @PrePersist
    private void prePersist(){
        this.fechaCreacion = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDate.now();
    }
}
