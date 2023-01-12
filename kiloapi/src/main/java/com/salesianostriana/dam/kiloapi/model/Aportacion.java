package com.salesianostriana.dam.kiloapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter


public class Aportacion {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "clase_id",
            foreignKey = @ForeignKey(name = "FK_APORTACION_CLASE"))
    private Clase clase;

    @JsonIgnore
    @OneToMany(mappedBy = "aportacion",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @Builder.Default
    private Set<DetalleAportacion> detalleAportacionList = new HashSet<>();



    // HELPERS DE GESTIÓN APORTACIÓN-DETALLEAPORTACIÓN
    public void addDetalleAportacion(DetalleAportacion detalleAportacion) {
        detalleAportacion.setAportacion(this);
        this.detalleAportacionList.add(detalleAportacion);
    }

    public void removeDetalleAportacion(DetalleAportacion detalleAportacion) {
        this.detalleAportacionList.remove(detalleAportacion);
        detalleAportacion.setAportacion(null);
    }


    // HELPERS GESTIÓN APORTACIÓN-CLASE
    public void addAportacionToClase(Clase clase) {
        this.clase = clase;
        clase.getAportacionList().add(this);
    }

    public void removeAportacionFromClase(Clase clase) {
        clase.getAportacionList().remove(this);
        this.clase = null;
    }
}