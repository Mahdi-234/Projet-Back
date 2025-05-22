package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.Entite.Parcours;
import com.Mahdi.hadiji.Repository.ParcoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcoursService {
    private final ParcoursRepository parcoursRepository;

    public ParcoursService(ParcoursRepository parcoursRepository) {
        this.parcoursRepository = parcoursRepository;
    }

    public List<Parcours> getAllParcours() {
        return parcoursRepository.findAll();
    }
}
