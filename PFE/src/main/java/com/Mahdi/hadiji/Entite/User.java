package com.Mahdi.hadiji.Entite;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String cin;  // Identifiant unique
    
    private String email;  // Email de l'utilisateur
    
    private String nom;  // Nom de l'utilisateur
    
    private String prenom;  // Prénom de l'utilisateur
    
    private int numTel;  // Numéro de téléphone
    
    private LocalDate dateNaissance;  // Date de naissance
    
    @Enumerated(EnumType.STRING)
    private Role role;  // Rôle de l'utilisateur

    @OneToOne(mappedBy = "compte", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Enseignant enseignant;
}