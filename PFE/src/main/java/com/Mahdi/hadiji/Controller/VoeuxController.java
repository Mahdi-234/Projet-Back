package com.Mahdi.hadiji.Controller;

import com.Mahdi.hadiji.DTO.VoeuxCreateDTO;
import com.Mahdi.hadiji.DTO.VoeuxDTO;
import com.Mahdi.hadiji.Service.VoeuxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voeux")
public class VoeuxController {

    @Autowired
    private VoeuxService voeuxService;

    @GetMapping("/enseignants")
    public List<VoeuxDTO> getVoeuxEnseignants() {
        return voeuxService.getVoeuxEnseignants();
    }



    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterVoeux(@RequestBody VoeuxCreateDTO dto) {
        voeuxService.ajouterVoeux(dto);
        return ResponseEntity.ok("Vœu ajouté avec succès !");
    }
    }
