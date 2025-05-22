package com.Mahdi.hadiji.Service;

import com.Mahdi.hadiji.Entite.User;
import com.Mahdi.hadiji.Repository.CompteRepository;
import com.Mahdi.hadiji.Security.JWToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWToken jwtUtil;

    public String login(String email, String password) {
        User compte = compteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(password, compte.getPassword())) {
            throw new BadCredentialsException("Mot de passe incorrect");
        }

        return jwtUtil.generateToken(compte);
    }
}
