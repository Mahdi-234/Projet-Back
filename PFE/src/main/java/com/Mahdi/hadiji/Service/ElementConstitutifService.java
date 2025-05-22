package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.DTO.ElementConstitutifDto;
import com.Mahdi.hadiji.DTO.EnseignantDto;
import com.Mahdi.hadiji.Entite.ElementConstitutif;
import com.Mahdi.hadiji.Entite.Enseignant;
import com.Mahdi.hadiji.Entite.Niveau;
import com.Mahdi.hadiji.Entite.Parcours;
import com.Mahdi.hadiji.Entite.Repartition;
import com.Mahdi.hadiji.Entite.Specialite;
import com.Mahdi.hadiji.Entite.UniteEnseignement;
import com.Mahdi.hadiji.Repository.ElementConstitutifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ElementConstitutifService {

    @Autowired
    private ElementConstitutifRepository repository;

    public List<ElementConstitutif> getAll() {
        return repository.findAll();
    }
    
    public List<ElementConstitutifDto> getAllDtos() {
        return repository.findAll().stream().map(ec -> {
            int totalCours = ec.getRepartitions().stream().mapToInt(Repartition::getNbGroupCours).sum();
            int totalTD = ec.getRepartitions().stream().mapToInt(Repartition::getNbGroupTD).sum();
            int totalTP = ec.getRepartitions().stream().mapToInt(Repartition::getNbGroupTP).sum();
            
            // Liste des enseignants ayant fait un vœu sur cet EC
            List<EnseignantDto> enseignants = ec.getVoeux().stream()
                .map(voeu -> {
                    Enseignant e = voeu.getEnseignant();
                    // on protège contre le cas où `compte` peut être null
                    String nom = e.getCompte() != null ? e.getCompte().getNom() : null;
                    String prenom = e.getCompte() != null ? e.getCompte().getPrenom() : null;
                    String email = e.getCompte() != null ? e.getCompte().getEmail() : null;
                    return new EnseignantDto(e.getId(), e.getMatriculeEns(), nom, prenom, email);
                })
                .distinct() // éviter les doublons d’enseignants s’il y a plusieurs vœux du même
                .collect(Collectors.toList());

            return new ElementConstitutifDto(
                ec.getId(),
                ec.getCode(),
                ec.getCoefficient(),
                ec.getNom(),
                ec.getCoursEns(),
                ec.getTdEns(),
                ec.getTpEns(),
                ec.getAutreEns(),
                ec.getNbHAutre(),
                ec.getNbHCours(),
                ec.getNbHTD(),
                ec.getNbHTP(),
                (int) ec.getNbHCours() - totalCours,
                (int) ec.getNbHTD() - totalTD,
                (int) ec.getNbHTP() - totalTP,
                ec.getUniteEnseignement().getNiveau().getNiveau(),
                ec.getUniteEnseignement().getNiveau().getParcours().getNom(),
                ec.getUniteEnseignement().getNiveau().getParcours().getSpecialite().getNom(),
                ec.getUniteEnseignement().getNumSemestre(),
                ec.getUniteEnseignement().getNom(),
                enseignants,
                ec.getModaliteEval().toString(),
                ec.getTypeCodeUE().toString(),
                ec.getUniteEnseignement().getNom(),
                ec.getUniteEnseignement().getNombreCredit(),
                ec.getTypeElement().getLabel()
            );
        }).collect(Collectors.toList());
    }


    public List<ElementConstitutifDto> getDtosByNiveauId(Long niveauId) {
        return repository.findByNiveauId(niveauId).stream().map(ec -> {
            int totalCours = ec.getRepartitions() != null ? ec.getRepartitions().stream().mapToInt(Repartition::getNbGroupCours).sum() : 0;
            int totalTD = ec.getRepartitions() != null ? ec.getRepartitions().stream().mapToInt(Repartition::getNbGroupTD).sum() : 0;
            int totalTP = ec.getRepartitions() != null ? ec.getRepartitions().stream().mapToInt(Repartition::getNbGroupTP).sum() : 0;

            List<EnseignantDto> enseignants = ec.getVoeux() != null ?
                ec.getVoeux().stream()
                    .map(voeu -> {
                        Enseignant e = voeu.getEnseignant();
                        String nom = (e.getCompte() != null) ? e.getCompte().getNom() : null;
                        String prenom = (e.getCompte() != null) ? e.getCompte().getPrenom() : null;
                        String email = (e.getCompte() != null) ? e.getCompte().getEmail() : null;
                        return new EnseignantDto(e.getId(), e.getMatriculeEns(), nom, prenom, email);
                    })
                    .distinct()
                    .collect(Collectors.toList())
                : Collections.emptyList();

            UniteEnseignement ue = ec.getUniteEnseignement();
            Niveau niveau = (ue != null) ? ue.getNiveau() : null;
            Parcours parcours = (niveau != null) ? niveau.getParcours() : null;
            Specialite specialite = (parcours != null) ? parcours.getSpecialite() : null;

            return new ElementConstitutifDto(
                ec.getId(),
                ec.getCode(),
                ec.getCoefficient(),
                ec.getNom(),
                ec.getCoursEns(),
                ec.getTdEns(),
                ec.getTpEns(),
                ec.getAutreEns(),
                ec.getNbHAutre(),
                ec.getNbHCours(),
                ec.getNbHTD(),
                ec.getNbHTP(),
                ec.getNbHCours() - totalCours,
                ec.getNbHTD() - totalTD,
                ec.getNbHTP() - totalTP,
                (niveau != null) ? niveau.getNiveau() : 0,
                (parcours != null) ? parcours.getNom() : null,
                (specialite != null) ? specialite.getNom() : null,
                (ue != null) ? ue.getNumSemestre() : 0,
                (ue != null) ? ue.getNom() : null,
                enseignants,
                ec.getModaliteEval().toString(),
                ec.getTypeCodeUE().toString(),
                ec.getUniteEnseignement().getNom(),
                ec.getUniteEnseignement().getNombreCredit(),
                ec.getTypeElement().getLabel()
            );
        }).collect(Collectors.toList());
    }


    public ElementConstitutif save(ElementConstitutif element) {
        return repository.save(element);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ElementConstitutif partialUpdate(Long id, Map<String, Object> updates) {
        ElementConstitutif element = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        
        updates.forEach((field, value) -> {
            switch (field) {
                case "nbHCours": element.setNbHCours((Integer) value); break;
                case "nbHTD": element.setNbHTD((Integer) value); break;
                case "nbHTP": element.setNbHTP((Integer) value); break;
                case "nbHAutre": element.setNbHAutre((Integer) value); break;
                case "coefficient": element.setCoefficient((Integer) value); break;
                case "totalCredit": element.setTotalCredit((Integer) value); break;
                // Ajouter d'autres champs si besoin
            }
        });
        return repository.save(element);
    }
}