package com.Mahdi.hadiji.Repository;

import com.Mahdi.hadiji.Entite.Niveau;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> 
{
	Optional<Niveau> findByNiveau(int niveau);

}