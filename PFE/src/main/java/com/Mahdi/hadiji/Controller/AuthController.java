package com.Mahdi.hadiji.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Mahdi.hadiji.Entite.User;
import com.Mahdi.hadiji.Repository.CompteRepository;
import com.Mahdi.hadiji.Security.JWToken;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWToken jwtUtil;

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Tentative de connexion avec email : " + request.getEmail());
        System.out.println("Mot de passe reçu: " + request.getPassword());

        // Recherche du compte dans la base de données par email
        Optional<User> optionalCompte = compteRepository.findByEmail(request.getEmail());

        if (optionalCompte.isEmpty()) {
            System.out.println("❌ Utilisateur non trouvé !");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");
        }

        User compte = optionalCompte.get();
        System.out.println("Mot de passe encodé en base : " + compte.getPassword());

        // Vérification du mot de passe
        if (!passwordEncoder.matches(request.getPassword(), compte.getPassword())) {
            System.out.println("❌ Mot de passe invalide !");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }

        System.out.println("✅ Connexion réussie !");

        // Génération du JWT
        String token = jwtUtil.generateToken(compte);

        // Extraction du rôle de l'utilisateur (ADMIN, ENSEIGNANT, etc.)
        String role = compte.getRole().name();

        System.out.println("🎭 Rôle détecté : " + role);

        return ResponseEntity.ok(new LoginResponse(token, role));
    }
}
