package com.Mahdi.hadiji.Repository;

import com.Mahdi.hadiji.Entite.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<User, Long> {

    // Trouver un compte par son email
    Optional<User> findByEmail(String email);

    // Trouver un compte par email (utile pour l’OAuth2 ou l'ajout manuel)
    Optional<User> findByEmailIgnoreCase(String email);

    // Vérifier si un CIN est déjà utilisé
    boolean existsByCin(String cin);

    // Vérifier si un email est déjà utilisé
    boolean existsByEmailIgnoreCase(String email);
}
