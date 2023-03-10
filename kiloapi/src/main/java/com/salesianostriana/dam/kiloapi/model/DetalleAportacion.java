package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class DetalleAportacion {

    @EmbeddedId
    private DetallesPK detallesPK;

    private double cantidadKg;

    @ManyToOne
    @JoinColumn(name = "aportacion_id", foreignKey = @ForeignKey(name = "FK_DETALLE_APORTACION"))

    private Aportacion aportacion;

    @ManyToOne
    @JoinColumn(name = "tipoAlimento_id", foreignKey = @ForeignKey(name = "FK_DETALLE_TIPO"))
    private TipoAlimento tipoAlimento;

}