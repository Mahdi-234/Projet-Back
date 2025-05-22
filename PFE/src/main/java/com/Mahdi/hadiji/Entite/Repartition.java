package com.Mahdi.hadiji.Entite;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Repartition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private int annee;    
    private int nbGroupCours;
    private int nbGroupTD;
    private int nbGroupTP;
    private int nbGroupAutre;
    
    @ManyToOne 
    private Enseignant enseignant;
    
    @ManyToOne
    private ElementConstitutif elementConstitutif; 

    }
