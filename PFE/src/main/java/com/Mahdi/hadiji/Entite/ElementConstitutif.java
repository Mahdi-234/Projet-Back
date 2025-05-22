package com.Mahdi.hadiji.Entite;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "element_constitutif")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ElementConstitutif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false)
    private int coefficient;

    @Column(nullable = false)
    private int coursEns;

    @Column(nullable = false)
    private int tdEns;

    @Column(nullable = false)
    private int tpEns;

    @Column(nullable = false)
    private int autreEns;

    @Enumerated(EnumType.STRING)
    private Modalite modaliteEval;

    private int nbHAutre;
    private int nbHCours;
    private int nbHTD;
    private int nbHTP;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false)
    private int totalCredit;

    @Enumerated(EnumType.STRING)
    private TypeElement typeElement;

    @Enumerated(EnumType.STRING)
    private CodeUE typeCodeUE;

    @ManyToOne
    @JoinColumn(name = "ue_id")
    private UniteEnseignement uniteEnseignement;

    @OneToMany(mappedBy = "elementConstitutif", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<Voeux> voeux;

    @OneToMany(mappedBy = "elementConstitutif")
    private List<Repartition> repartitions;

}
