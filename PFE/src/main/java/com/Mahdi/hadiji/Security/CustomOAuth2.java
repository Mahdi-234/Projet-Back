//package com.Mahdi.hadiji.Security;
//
//import com.Mahdi.hadiji.Entite.Compte;
//import com.Mahdi.hadiji.Repository.CompteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomOAuth2 implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    @Autowired
//    private CompteRepository compteRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        // Charger les infos depuis Google
//        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
//        String email = oAuth2User.getAttribute("email");
//        System.out.println("👉 Email reçu via Google: " + email);
//
//        if (email == null) {
//            throw new OAuth2AuthenticationException("Email non trouvé dans les informations fournies par Google");
//        }
//
//        // Vérifier si le compte existe dans la base
//        Compte compte = compteRepository.findByEmailIgnoreCase(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Aucun compte associé à cet email: " + email));
//
//        // Retourner un utilisateur enrichi avec ses rôles
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + compte.getRole())),
//                oAuth2User.getAttributes(),
//                "name"
//        );
//    }
//}
//
//
//
////
//////🧠 Ce que ça fait :
//////
//////Récupère les infos du compte Microsoft (via OAuth).
//////
//////Vérifie si l’email existe dans ta base.
//////
//////✅ Oui : on continue.
//////
//////❌ Non : on bloque la connexion avec un message.