package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.Entite.Niveau;
import com.Mahdi.hadiji.Entite.UniteEnseignement;
import com.Mahdi.hadiji.Repository.NiveauRepository;
import com.Mahdi.hadiji.Repository.UniteEnseignementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauService {

    @Autowired
    private NiveauRepository niveauRepository;
    
    @Autowired
    private UniteEnseignementRepository uniteEnseignementRepository;
    public List<Niveau> getAll() {
        return niveauRepository.findAll();
    }
    
    public Niveau save(Niveau niveau) {
        return niveauRepository.save(niveau);
    }
    
    

    public void supprimerNiveau(Long id) {
        Niveau niveau = niveauRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Niveau introuvable"));
        // Vérification en base (plus via la propriété)
        long nbUE = uniteEnseignementRepository.countByNiveauId(id);
        if (nbUE > 0) {
            throw new RuntimeException("Impossible de supprimer : ce niveau contient déjà des unités d'enseignement.");
        }
        niveauRepository.deleteById(id);
    }
}