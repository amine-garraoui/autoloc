package com.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Vehicule> vehicules = new HashSet<>();
}
