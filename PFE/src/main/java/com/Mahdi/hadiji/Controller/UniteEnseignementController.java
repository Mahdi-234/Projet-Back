package com.Mahdi.hadiji.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Mahdi.hadiji.Entite.UniteEnseignement;
import com.Mahdi.hadiji.Service.UniteEnseignementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/unite-enseignement")
public class UniteEnseignementController {

    private static final Logger logger = LoggerFactory.getLogger(UniteEnseignementController.class);

    @Autowired
    private UniteEnseignementService uniteEnseignementService;

    @GetMapping("/")
    public List<UniteEnseignement> getAllUniteEnseignements() {
        logger.info("Received request to get all UniteEnseignements");
        return uniteEnseignementService.getAll();
    }
}