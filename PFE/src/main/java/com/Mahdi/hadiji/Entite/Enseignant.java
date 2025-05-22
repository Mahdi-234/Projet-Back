package com.Mahdi.hadiji.Entite;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "enseignant")
@Data
public class Enseignant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
     
    @Column(nullable = false, unique = true)
    private int matriculeEns;
    
    private LocalDate dateEntree;  
    private LocalDate dateSortie;
    
    
    @Column(length = 100)
    private String experience;

    @OneToOne(optional = true)//ma3neha tnjm tkoun nullable
   private User compte;
    	
    @ManyToOne
    @JoinColumn(name = "grades_id")  // IMPORTANT : le nom correspond à la colonne dans la BDD
   private  Grade grades;
}
