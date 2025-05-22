package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.DTO.VoeuxCreateDTO;
import com.Mahdi.hadiji.DTO.VoeuxDTO;
import com.Mahdi.hadiji.Entite.ElementConstitutif;
import com.Mahdi.hadiji.Entite.Enseignant;
import com.Mahdi.hadiji.Entite.Voeux;
import com.Mahdi.hadiji.Repository.ElementConstitutifRepository;
import com.Mahdi.hadiji.Repository.EnseignantRepository;
import com.Mahdi.hadiji.Repository.VoeuxRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class VoeuxService {

	@Autowired
	private EnseignantRepository enseignantRepository;
    @Autowired
    private ElementConstitutifRepository elementConstitutifRepository;
    @Autowired
    private VoeuxRepository voeuxRepository;   
    @PersistenceContext
    private EntityManager entityManager;

    public List<VoeuxDTO> getVoeuxEnseignants() {
    	List<Object[]> rows = entityManager.createNativeQuery(
//    		    "SELECT " +
//    		    "  u.nom AS nom_enseignant, " +
//    		    "  ec.nom AS nom_matiere, " +
//    		    "  CONCAT(" +
//    		    "    IFNULL(CONCAT('TD(', IFNULL(v.nb_groupe_td,0), ') '), ''), " +
//    		    "    IFNULL(CONCAT('Cours(', IFNULL(v.nb_groupe_cours,0), ') '), ''), " +
//    		    "    IFNULL(CONCAT('TP(', IFNULL(v.nb_groupe_tp,0), ') '), ''), " +
//    		    "    IFNULL(CONCAT('Autre(', IFNULL(v.nb_groupe_autre,0), ') '), '')" +
//    		    "  ) AS types, " +
//    		    "  (IFNULL(ec.nbHTD,0) + IFNULL(ec.nbHTP,0) + IFNULL(ec.nbHCours,0) + IFNULL(ec.nbHAutre,0)) AS nb_disponible, " +
//    		    "  v.date_choix, " +
//    		    "  (" +
//    		    "      CASE " +
//    		    "          WHEN g.grade = 'Professeur d’enseignement supérieur' THEN 4 " +
//    		    "          WHEN g.grade = 'Professeur agrégé' THEN 3 " +
//    		    "          WHEN g.grade = 'Maître assistant' THEN 2 " +
//    		    "          WHEN g.grade = 'Assistant' THEN 1 " +
//    		    "          WHEN g.grade = 'Professeur de l’enseignement secondaire' THEN 1 " +
//    		    "          WHEN g.grade = 'Contractuel avec doctorat' THEN 2 " +
//    		    "          WHEN g.grade = 'Contractuel inscrit en doctorat' THEN 1 " +
//    		    "          ELSE 1 " +
//    		    "      END * 2 + IFNULL(ens.experience, 0) " +
//    		    "  ) AS priorite, " +
//    		    "  (SELECT COUNT(*) FROM voeux v2 WHERE v2.element_constitutif_id = ec.id) AS nb_fois_matiere, " +
//    		    "  ens.experience AS experience, " +
//    		    "  g.grade AS nom_grade " +
//    		    "FROM element_constitutif ec " +
//    		    "JOIN uniteenseignement ue ON ec.ue_id = ue.id " +
//    		    "INNER JOIN voeux v ON v.element_constitutif_id = ec.id " +   // <--- MODIF ICI !
//    		    "LEFT JOIN enseignant ens ON v.enseignant_id = ens.id " +
//    		    "LEFT JOIN user u ON ens.compte_id = u.id " +
//    		    "LEFT JOIN grade g ON ens.grades_id = g.id " +
//    		    "ORDER BY ec.id, priorite DESC, v.date_choix"
//    		).getResultList();
    			"SELECT " +
                "  u.nom AS nom_enseignant, " +
                "  ec.nom AS nom_matiere, " +
                "  CONCAT(" +
                "    IFNULL(CONCAT('TD(', IFNULL(v.nb_groupe_td,0), ') '), ''), " +
                "    IFNULL(CONCAT('Cours(', IFNULL(v.nb_groupe_cours,0), ') '), ''), " +
                "    IFNULL(CONCAT('TP(', IFNULL(v.nb_groupe_tp,0), ') '), ''), " +
                "    IFNULL(CONCAT('Autre(', IFNULL(v.nb_groupe_autre,0), ')'), '')" +
                "  ) AS types, " +
                "  (IFNULL(ec.nbHTD,0) + IFNULL(ec.nbHTP,0) + IFNULL(ec.nbHCours,0) + IFNULL(ec.nbHAutre,0)) AS nb_disponible, " +
                "  v.date_choix, " +
                "  (" +
                "      CASE " +
                "          WHEN g.grade = 'Professeur d’enseignement supérieur' THEN 4 " +
                "          WHEN g.grade = 'Professeur agrégé' THEN 3 " +
                "          WHEN g.grade = 'Maître assistant' THEN 2 " +
                "          WHEN g.grade = 'Assistant' THEN 1 " +
                "          WHEN g.grade = 'Professeur de l’enseignement secondaire' THEN 1 " +
                "          WHEN g.grade = 'Contractuel avec doctorat' THEN 2 " +
                "          WHEN g.grade = 'Contractuel inscrit en doctorat' THEN 1 " +
                "          ELSE 1 " +
                "      END * 2 + IFNULL(ens.experience, 0) " +
                "  ) AS priorite, " +
                "  (SELECT COUNT(*) FROM voeux v2 WHERE v2.element_constitutif_id = ec.id) AS nb_fois_matiere, " +
                "  ens.experience AS experience, " +
                "  g.grade AS nom_grade " +
                "FROM element_constitutif ec " +
                "JOIN uniteenseignement ue ON ec.ue_id = ue.id " +
                "INNER JOIN voeux v ON v.element_constitutif_id = ec.id " +
                "LEFT JOIN enseignant ens ON v.enseignant_id = ens.id " +
                "LEFT JOIN user u ON ens.compte_id = u.id " +
                "LEFT JOIN grade g ON ens.grades_id = g.id " +
                "ORDER BY ec.id, priorite DESC, v.date_choix"
            ).getResultList();

    	List<VoeuxDTO> list = new ArrayList<>();
        for (Object[] row : rows) {
            VoeuxDTO dto = new VoeuxDTO();
            dto.setNomEnseignant(row[0] != null ? row[0].toString() : null);
            dto.setNomMatiere(row[1] != null ? row[1].toString() : null);
            dto.setTypes(row[2] != null ? row[2].toString().trim() : null);
            dto.setNbDisponible(row[3] != null ? ((Number) row[3]).intValue() : 0);
            dto.setDateVoeux(row[4] != null ? row[4].toString() : null);
            dto.setPriorite(row[5] != null ? ((Number) row[5]).intValue() : 0);
            dto.setNbFoisMatiere(row[6] != null ? ((Number) row[6]).intValue() : 0);
            dto.setExperience(row[7] != null ? row[7].toString() : null);
            dto.setNomGrade(row[8] != null ? row[8].toString() : null);
            list.add(dto);
        }
        return list;
    
    }


    private int toInt(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        } else if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Impossible de convertir en entier : " + obj, e);
            }
        } else {
            throw new IllegalArgumentException("Type non supporté pour conversion en entier : " + obj);
        }
    }




public void ajouterVoeux(VoeuxCreateDTO dto) {
    Enseignant enseignant = enseignantRepository.findById(dto.getEnseignantId())
        .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
    ElementConstitutif ec = elementConstitutifRepository.findById(dto.getElementConstitutifId())
        .orElseThrow(() -> new RuntimeException("Elément constitutif introuvable"));
    Voeux voeux = Voeux.builder()
        .dateChoix(LocalDateTime.now())
        .enseignant(enseignant)
        .elementConstitutif(ec)
        .nbGroupeCours(dto.getNbGroupeCours())
        .nbGroupeTd(dto.getNbGroupeTd())
        .nbGroupeTp(dto.getNbGroupeTp())
        .nbGroupeAutre(dto.getNbGroupeAutre())
        .build();
    voeuxRepository.save(voeux);
}
}