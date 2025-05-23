package com.Mahdi.hadiji.Controller;

import com.Mahdi.hadiji.DTO.DemandeDTO;
import com.Mahdi.hadiji.Service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@CrossOrigin(origins = "http://localhost:3000")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @GetMapping
    public ResponseEntity<List<DemandeDTO>> getAllDemandes() {
        List<DemandeDTO> demandes = demandeService.getDemandes();
        return ResponseEntity.ok(demandes); // ✅ PAS de writeValueAsString ici
    }
}