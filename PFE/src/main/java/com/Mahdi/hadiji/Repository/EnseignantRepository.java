package com.Mahdi.hadiji.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Mahdi.hadiji.Entite.Enseignant;
import com.Mahdi.hadiji.Entite.User;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> 
{
    Optional<Enseignant> findByCompte(User compte);


}
