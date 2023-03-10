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
@NamedEntityGraph
        (name="destinatario-con-caja",
                attributeNodes = {
                        @NamedAttributeNode(value = "cajaList",
                                subgraph = "caja-tiene")
                }, subgraphs = {
                @NamedSubgraph(name="caja-tiene",
                        attributeNodes = {
                                @NamedAttributeNode("tieneList")
                        })
        })
public class Destinatario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String direccion;

    private String personaContacto;

    private String telefono;

    @OneToMany(mappedBy = "destinatario", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Caja> cajaList = new ArrayList<>();

    @PreRemove
    public void setNullCajas(){cajaList.forEach(c -> c.setDestinatario(null));}
}