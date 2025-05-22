package com.Mahdi.hadiji.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.Mahdi.hadiji.Entite.User;

import java.util.Date;
@Component
public class JWToken {
    @Value("${jwt.secret}")
    private String secretKey;  // La clé secrète pour signer le JWT

    // Durée d'expiration du token (par exemple 1 heure)
    private static final long EXPIRATION_TIME = 360000000;  // 1 heure en millisecondes

    // Générer un token JWT
    public String generateToken(User compte) {
        return Jwts.builder()
                .setSubject(compte.getCin())  // Nom de l'utilisateur ou autre info importante
                .setIssuedAt(new Date())  // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Expiration dans 1 heure
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Signature avec la clé secrète
                .compact();
    }

    // Extraire le nom d'utilisateur du token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraire une revendication spécifique (par exemple expiration, subject, etc.)
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)  // Utiliser la clé secrète pour valider le token
                .parseClaimsJws(token)
                .getBody();
    }

    // Extraire une revendication spécifique du token
    private <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    // Vérifier si le token est expiré
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraire la date d'expiration du token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Valider le token (vérifie que l'utilisateur correspond et que le token n'est pas expiré)
    public Boolean validateToken(String token, String username) {
        final String usernameInToken = extractUsername(token);
        return (usernameInToken.equals(username) && !isTokenExpired(token));
    }

    // Interface fonctionnelle pour extraire une revendication spécifique
    @FunctionalInterface
    public interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }
}
