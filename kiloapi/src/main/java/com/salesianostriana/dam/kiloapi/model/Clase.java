package com.salesianostriana.dam.kiloapi.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Fetch(FetchMode.SUBSELECT)
    @Builder.Default
    private Set<Aportacion> aportacionList = new HashSet<>();


    @PreRemove
    public void setNullAportacion() {
        aportacionList.forEach(a -> {
            a.setClase(null);
        });
    }
}