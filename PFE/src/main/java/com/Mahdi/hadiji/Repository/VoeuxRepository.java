package com.Mahdi.hadiji.Repository;

import com.Mahdi.hadiji.Entite.Voeux;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoeuxRepository extends JpaRepository<Voeux, Long> {

    // Méthode pour compter le nombre de groupes affectés à une matière spécifique
    @Query("SELECT SUM(v.nbGroupeCours + v.nbGroupeTd + v.nbGroupeTp + v.nbGroupeAutre) " +
           "FROM Voeux v WHERE v.elementConstitutif.id = :elementId")
    int countGroupesAffectes(@Param("elementId") Long elementId);
    
    // Méthode pour récupérer les vœux d'un enseignant pour une matière spécifique
    @Query("SELECT v FROM Voeux v WHERE v.enseignant.id = :enseignantId AND v.elementConstitutif.id = :elementId")
    Voeux findByEnseignantAndElementConstitutif(@Param("enseignantId") Long enseignantId, 
                                                @Param("elementId") Long elementId);

    // Méthode pour récupérer tous les vœux d'un enseignant
    @Query("SELECT v FROM Voeux v WHERE v.enseignant.id = :enseignantId")
    List<Voeux> findByEnseignantId(@Param("enseignantId") Long enseignantId);
}
