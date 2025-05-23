package com.Mahdi.hadiji.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Mahdi.hadiji.Entite.Parcours;

public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
    // Pas besoin d’ajouter de méthode pour un GET all
}
