package com.Mahdi.hadiji.Repository;

import com.Mahdi.hadiji.Entite.ElementConstitutif;
import com.Mahdi.hadiji.Entite.Repartition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementConstitutifRepository extends JpaRepository<ElementConstitutif, Long> {

    @Query("SELECT ec FROM ElementConstitutif ec JOIN ec.uniteEnseignement ue WHERE ue.niveau.id = :niveauId")
    List<ElementConstitutif> findByNiveauId(@Param("niveauId") Long niveauId);

    // Nouvelle méthode pour récupérer les matières et leur nombre d'heures
    @Query("SELECT ec FROM ElementConstitutif ec")
    List<ElementConstitutif> findAllWithHours();
    
}
