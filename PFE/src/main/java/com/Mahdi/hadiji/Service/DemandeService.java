package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.DTO.DemandeDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DemandeDTO> getDemandes() {
    	List<Object[]> rows = entityManager.createNativeQuery("""
   SELECT 
    CONCAT(e.prenom, ' ', e.nom) AS nom_enseignant,
    ec.nom AS nom_matiere,
    0 AS nb_affectations,
    e.experience,
    g.grade AS nom_grade,
    (
        (CASE 
            WHEN g.grade = 'Professeur d’enseignement supérieur' THEN 4
            WHEN g.grade = 'Professeur agrégé' THEN 3
            WHEN g.grade = 'Maître assistant' THEN 2
            WHEN g.grade = 'Assistant' THEN 1
            WHEN g.grade = 'Professeur de l’enseignement secondaire' THEN 1
            WHEN g.grade = 'Contractuel avec doctorat' THEN 2
            WHEN g.grade = 'Contractuel inscrit en doctorat' THEN 1
            ELSE 1
        END) * 2
        + e.experience
    ) AS priorite
FROM voeux v
JOIN enseignant e ON e.id = v.enseignant
JOIN grade g ON e.grades_id = g.id
JOIN element_constitutif ec ON v.element_constitutif = ec.id
ORDER BY priorite DESC;


    		""").getResultList();


        List<DemandeDTO> result = new ArrayList<>();
        for (Object[] row : rows) {
            DemandeDTO dto = new DemandeDTO();
            dto.setNomEnseignant((String) row[0]);
            dto.setNomMatiere((String) row[1]);
            dto.setNbFoisMatiere(toInt(row[2]));
            dto.setExperience(String.valueOf(row[3]));
            dto.setNomGrade((String) row[4]);
            dto.setPriorite(toInt(row[5]));

            dto.setTypes(null);
            dto.setNbDisponible(0);
            dto.setDateVoeux(null);

            result.add(dto);
        }

        return result;
    }

    private int toInt(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        } else if (obj instanceof String) {
            try {
                return (int) Double.parseDouble((String) obj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Impossible de convertir en entier : " + obj, e);
            }
        } else {
            throw new IllegalArgumentException("Type non supporté pour conversion en entier : " + obj);
        }
    }
}