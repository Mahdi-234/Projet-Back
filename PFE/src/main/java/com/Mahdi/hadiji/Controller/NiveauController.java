package com.Mahdi.hadiji.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Mahdi.hadiji.DTO.ElementConstitutifDto;
import com.Mahdi.hadiji.Entite.ElementConstitutif;
import com.Mahdi.hadiji.Entite.Niveau;
import com.Mahdi.hadiji.Repository.NiveauRepository;
import com.Mahdi.hadiji.Repository.UniteEnseignementRepository;
import com.Mahdi.hadiji.Service.ElementConstitutifService;
import com.Mahdi.hadiji.Service.NiveauService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/niveaux")
public class NiveauController {

 @Autowired
 private NiveauService niveauService;
 @Autowired
 private NiveauRepository niveauRepository;

 @Autowired
 private ElementConstitutifService elementConstitutifService;
 @Autowired
 private UniteEnseignementRepository uniteEnseignementRepository;

 // Récupérer tous les niveaux
 @GetMapping("/")
 public List<Niveau> getAllNiveaux() {
     return niveauService.getAll();
 }

 // Récupérer les éléments constitutifs d'un niveau par son ID
 @GetMapping("/{niveauId}/element-constitutifs")
 public List<ElementConstitutifDto> getElementConstitutifsByNiveau(@PathVariable Long niveauId) {
     return elementConstitutifService.getDtosByNiveauId(niveauId);
 }
 
 @PostMapping("/")
 public ResponseEntity<Niveau> createNiveau(@RequestBody Niveau niveau) {
     Niveau savedNiveau = niveauService.save(niveau);
     return new ResponseEntity<>(savedNiveau, HttpStatus.CREATED);
 }
 @DeleteMapping("/{id}")
 public ResponseEntity<?> deleteNiveau(@PathVariable Long id) {
     try {
         niveauService.supprimerNiveau(id);
         // 204 = No Content (succès, pas de retour)
         return ResponseEntity.noContent().build();
     } catch (RuntimeException ex) {
         // 409 = Conflit logique (ex : UE encore rattachées)
         return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
     } catch (Exception ex) {
         // 500 = Erreur serveur
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne");
     }
 }

}