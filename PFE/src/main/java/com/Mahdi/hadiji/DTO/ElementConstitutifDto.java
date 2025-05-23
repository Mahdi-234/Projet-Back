package com.Mahdi.hadiji.DTO;

import java.lang.invoke.StringConcatFactory;
import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ElementConstitutifDto {

	private Long id;
    private String code;
    private int coefficient;
    private String nom;
    private int coursEns;
    private int tdEns;
    private int tpEns;
    private int autreEns;
    private int nbHAutre;
    private int nbHCours;
    private int nbHTD;
    private int nbHTP;
    private int groupCoursRestant;
    private int groupTDRestant;
    private int groupTPRestant;
    private int niveau;
    private String parcours;
    private String specialite;
    private int numSemestre;
    private String uniteEnseignement;
    List<EnseignantDto> enseignants;
    private String modalite;
    private String typeUe;
    private String nomUe ;
    private int credit;
    private String typeElt;
}