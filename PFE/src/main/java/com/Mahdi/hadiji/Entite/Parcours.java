package com.Mahdi.hadiji.Entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Parcours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    // Enumération représentant le type de formation
    @Enumerated(EnumType.STRING)
    private TypeFormation type;

     
    @OneToMany(mappedBy = "parcours")
    @JsonIgnore
    private List<Niveau> niveaux;
  
    @ManyToOne
    @JoinColumn(name = "specialite_id")
    private Specialite specialite;  // Assurez-vous que cette propriété existe

}
