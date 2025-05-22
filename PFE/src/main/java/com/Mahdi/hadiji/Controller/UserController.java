// Controller REST
package com.Mahdi.hadiji.Controller;

import com.Mahdi.hadiji.Entite.*;
import com.Mahdi.hadiji.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    @Autowired private CompteRepository userRepo;
    @Autowired private EnseignantRepository enseignantRepo;
    @Autowired private GradeRepository gradeRepo;
    @Autowired private AffeChefDepRepository affectationRepo;

    @PostMapping("/ajouter")
    @Transactional
    public ResponseEntity<?> ajouterUtilisateur(@RequestBody User user) {
        if (userRepo.existsByCin(user.getCin())) {
            return ResponseEntity.badRequest().body("CIN déjà utilisé");
        }

        user.setPassword(user.getPassword()); // encoder si nécessaire
        User savedUser = userRepo.save(user);

        if (user.getRole() == Role.ENSEIGNANT && user.getEnseignant() != null) {
            Enseignant ens = user.getEnseignant();
            ens.setCompte(savedUser);

            if (ens.getGrades() != null && ens.getGrades().getId() != null) {
                gradeRepo.findById(ens.getGrades().getId()).ifPresent(ens::setGrades);
            }

            enseignantRepo.save(ens);
        }

        if (user.getRole() == Role.CHEFDEPARTEMENT) {
            AffectionChefDepartement affectation = new AffectionChefDepartement();
            affectation.setDateAffectation(new Date());
            affectation.setDateFinAffectation(null);
            affectation.setUtilisateur(savedUser);

            Enseignant enseignantLie = user.getEnseignant();
            if (enseignantLie != null) {
                enseignantLie.setCompte(savedUser);
                if (enseignantLie.getGrades() != null && enseignantLie.getGrades().getId() != null) {
                    gradeRepo.findById(enseignantLie.getGrades().getId()).ifPresent(enseignantLie::setGrades);
                }
                Enseignant savedEns = enseignantRepo.save(enseignantLie);
                affectation.setEnseignant(savedEns);
            }

            affectationRepo.save(affectation);
        }

        return ResponseEntity.ok("Utilisateur créé avec succès");
    }
}
