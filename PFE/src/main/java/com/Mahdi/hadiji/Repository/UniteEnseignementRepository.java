package com.Mahdi.hadiji.Repository;

import com.Mahdi.hadiji.Entite.UniteEnseignement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniteEnseignementRepository extends JpaRepository<UniteEnseignement, Long> {

    @Query("SELECT ue FROM UniteEnseignement ue JOIN FETCH ue.elementConstitutifs WHERE ue.niveau.id = :niveauId")
    List<UniteEnseignement> findByNiveauIdWithElements(@Param("niveauId") Long niveauId);
    
    long countByNiveauId(Long niveauId);

}