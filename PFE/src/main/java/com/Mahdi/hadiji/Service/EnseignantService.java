package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.Entite.User;
import com.Mahdi.hadiji.Entite.Enseignant;
import com.Mahdi.hadiji.Entite.Grade;
import com.Mahdi.hadiji.Repository.CompteRepository;
import com.Mahdi.hadiji.Repository.EnseignantRepository;
import com.Mahdi.hadiji.Repository.GradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository repository;
    
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private GradeRepository gradeRepository;

    public List<Enseignant> getAll() {
        return repository.findAll();
    }

    public Enseignant findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Enseignant save(Enseignant enseignant) {
        return repository.save(enseignant);
    }
    
    
 

public Enseignant update(Long id, Enseignant updatedEnseignant) {
    Optional<Enseignant> optional = repository.findById(id);
    if (optional.isPresent()) {
        Enseignant existing = optional.get();
        // Mise à jour des champs User via getCompte()
        if (existing.getCompte() != null && updatedEnseignant.getCompte() != null) {
            existing.getCompte().setCin(updatedEnseignant.getCompte().getCin());
            existing.getCompte().setNom(updatedEnseignant.getCompte().getNom());
            existing.getCompte().setPrenom(updatedEnseignant.getCompte().getPrenom());
            existing.getCompte().setNumTel(updatedEnseignant.getCompte().getNumTel());
            existing.getCompte().setDateNaissance(updatedEnseignant.getCompte().getDateNaissance());
            // Autres champs User si besoin
        }
        // Mise à jour des champs Enseignant
        existing.setMatriculeEns(updatedEnseignant.getMatriculeEns());
        existing.setDateEntree(updatedEnseignant.getDateEntree());
        existing.setDateSortie(updatedEnseignant.getDateSortie());
        existing.setExperience(updatedEnseignant.getExperience());
        // ... autres champs Enseignant si besoin

        return repository.save(existing);
    } else {
        return null;
    }
}

    public void delete(Long id) {
        repository.deleteById(id);
    }
}