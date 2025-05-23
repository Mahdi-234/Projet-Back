package com.Mahdi.hadiji.Controller;

import com.Mahdi.hadiji.Entite.Parcours;
import com.Mahdi.hadiji.Service.ParcoursService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcours")
public class ParcoursController {

    private final ParcoursService parcoursService;

    public ParcoursController(ParcoursService parcoursService) {
        this.parcoursService = parcoursService;
    }

    @GetMapping
    public List<Parcours> getAllParcours() {
        return parcoursService.getAllParcours();
    }
}
