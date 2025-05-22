package com.Mahdi.hadiji.Entite;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
@Entity
@Getter
@Setter
public class AffectionChefDepartement
{
	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;	    
	    private Date dateAffectation;
	    private Date dateFinAffectation;
	    
	    @ManyToOne
	    @JoinColumn(name = "enseignant_id")
	    private Enseignant enseignant;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User utilisateur;


}
 