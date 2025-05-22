package com.Mahdi.hadiji.Controller;

import com.Mahdi.hadiji.DTO.ElementConstitutifDto;
import com.Mahdi.hadiji.Entite.ElementConstitutif;
import com.Mahdi.hadiji.Service.ElementConstitutifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/element")
@CrossOrigin(origins = "http://localhost:5173")
public class ElementConstitutifController {

    @Autowired
    private ElementConstitutifService service;

    @GetMapping
    public List<ElementConstitutifDto> getAll() {
        return service.getAllDtos();
    }

    @CrossOrigin(origins = "http://localhost:5173")

    @GetMapping("/niveau/{niveauId}")
    public List<ElementConstitutifDto> getElementsByNiveau(@PathVariable Long niveauId) {
        return service.getDtosByNiveauId(niveauId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ElementConstitutif element) {
        try {
            ElementConstitutif savedElement = service.save(element);
            return ResponseEntity.ok(savedElement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'ajout de l'élément : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ElementConstitutif> partialUpdate(
        @PathVariable Long id,
        @RequestBody Map<String, Object> updates
    ) {
        try {
            ElementConstitutif updated = service.partialUpdate(id, updates);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}