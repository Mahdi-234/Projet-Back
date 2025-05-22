package com.Mahdi.hadiji.Entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UniteEnseignement")
public class UniteEnseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private int nombreCredit;

    private int numSemestre;

    @ManyToOne
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;
    
    
    @OneToMany(mappedBy = "uniteEnseignement")
    @JsonIgnore
    private List<ElementConstitutif> elementConstitutifs;
}