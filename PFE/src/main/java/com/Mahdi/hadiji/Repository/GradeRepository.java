package com.Mahdi.hadiji.Repository;

import com.Mahdi.hadiji.Entite.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    // JpaRepository fournit déjà des méthodes pour récupérer toutes les entrées
}
