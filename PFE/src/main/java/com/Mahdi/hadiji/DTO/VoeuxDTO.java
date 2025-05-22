package com.Mahdi.hadiji.DTO;

import lombok.Data;

@Data
public class VoeuxDTO {
    private String nomEnseignant;
    private String nomMatiere;
    private String types;         // ex: "TD(2), cours(1), TP(4)"
    private int nbDisponible;
    private String dateVoeux;
    private int priorite;
    private int nbFoisMatiere;
    private String experience;
    private String nomGrade;
}
