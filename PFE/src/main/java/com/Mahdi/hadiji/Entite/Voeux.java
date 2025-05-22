package com.Mahdi.hadiji.Entite;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voeux")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Voeux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_choix", nullable = false)
    private LocalDateTime dateChoix;

    @Column(name = "nb_groupe_cours", nullable = false)
    private int nbGroupeCours;

    @Column(name = "nb_groupe_td", nullable = false)
    private int nbGroupeTd;

    @Column(name = "nb_groupe_tp", nullable = false)
    private int nbGroupeTp;

    @Column(name = "nb_groupe_autre", nullable = false)
    private int nbGroupeAutre;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusVoeux status = StatusVoeux.EN_ATTENTE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "element_constitutif_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private ElementConstitutif elementConstitutif;

}
