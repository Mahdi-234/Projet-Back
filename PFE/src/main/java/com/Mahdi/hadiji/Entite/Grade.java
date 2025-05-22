package com.Mahdi.hadiji.Entite;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity

@Data
public class Grade 
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private float charge;
	    private String grade;
	    
		@Enumerated(EnumType.STRING)
	    private TypeEnseignement typeEnseignement;
	    

}