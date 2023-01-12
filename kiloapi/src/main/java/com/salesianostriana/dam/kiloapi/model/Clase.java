package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@NamedEntityGraph
        (name="clase-con-aportaciones",
                attributeNodes = {
                        @NamedAttributeNode(value = "aportacionList",
                                subgraph = "detalles-aportacion")
                }, subgraphs = {
                @NamedSubgraph(name="detalles-aportacion",
                        attributeNodes = {
                                @NamedAttributeNode("detalleAportacionList")
                        })
        })
public class Clase {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String tutor;

    @OneToMany(mappedBy = "clase",fetch = FetchType.LAZY)
    @Builder.Default
    private List<Aportacion> aportacionList = new ArrayList<>();


    @PreRemove
    public void setNullAportacion() {
        aportacionList.forEach(a -> {
            a.setClase(null);
        });
    }
}