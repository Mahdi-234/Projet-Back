package com.Mahdi.hadiji.Controller;

import com.Mahdi.hadiji.Entite.Enseignant;
import com.Mahdi.hadiji.Entite.Grade;
import com.Mahdi.hadiji.Service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/enseignants")
@CrossOrigin(origins = "http://localhost:3000")
public class EnseignantController {

    @Autowired
    private EnseignantService service;

    @GetMapping
    public List<Enseignant> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getById(@PathVariable Long id) {
        Enseignant enseignant = service.findById(id);
        return enseignant != null ? ResponseEntity.ok(enseignant) : ResponseEntity.notFound().build();
    }
   
    @PostMapping("/add")
    public ResponseEntity<Enseignant> create(@RequestBody Enseignant enseignant) {
        Enseignant created = service.save(enseignant);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Enseignant> update(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        Enseignant updated = service.update(id, enseignant);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}