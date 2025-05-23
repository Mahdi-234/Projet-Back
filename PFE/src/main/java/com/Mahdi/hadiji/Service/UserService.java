package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.Entite.User;
import com.Mahdi.hadiji.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private CompteRepository compteRepository;

    // Trouver un utilisateur par email
    public Optional<User> findUserByEmail(String email) {
        return compteRepository.findByEmail(email);
    }

    // Ajouter d'autres méthodes nécessaires, comme la création de comptes, la modification, etc.
}
