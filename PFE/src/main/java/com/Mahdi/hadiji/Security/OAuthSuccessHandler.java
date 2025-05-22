/*package com.Mahdi.hadiji.Security;

import com.Mahdi.hadiji.Entite.Compte;
import com.Mahdi.hadiji.Repository.CompteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWToken jwtToken;

    @Autowired
    private CompteRepository compteRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();

        String email = user.getAttribute("email");

        Compte compte = compteRepository.findByCin(email).orElse(null);
        if (compte == null) {
            response.sendRedirect("http://localhost:5173/login?error=email_not_found");
            return;
        }

        String jwt = jwtToken.generateToken(compte);
        String role = compte.getRole().name();
        String cin = compte.getCin();  // si tu veux renvoyer aussi le cin

        String redirectUrl = "http://localhost:5173/oauth-callback" +
                "?token=" + URLEncoder.encode(jwt, StandardCharsets.UTF_8) +
                "&role=" + URLEncoder.encode(role, StandardCharsets.UTF_8) +
                "&cin=" + URLEncoder.encode(cin, StandardCharsets.UTF_8);

        response.sendRedirect(redirectUrl);
    }
}
*/