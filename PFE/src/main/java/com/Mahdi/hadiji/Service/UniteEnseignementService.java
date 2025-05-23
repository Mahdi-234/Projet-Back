package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.Entite.UniteEnseignement;
import com.Mahdi.hadiji.Repository.UniteEnseignementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniteEnseignementService {

    @Autowired
    private UniteEnseignementRepository uniteEnseignementRepository;

    public List<UniteEnseignement> getAll() {
        return uniteEnseignementRepository.findAll();
    }
}