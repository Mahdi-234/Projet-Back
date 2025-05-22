package com.Mahdi.hadiji.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) 
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()  // Autoriser toutes les routes /api/** sans authentification
                .anyRequest().authenticated()  // Toute autre route nécessite une authentification
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // Filtrer le JWT pour chaque requête
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Pas de gestion de session, car on utilise JWT

        return http.build();
    }
}





































//package com.Mahdi.hadiji.Security;
//
////Autoriser /api/auth/** librement.
////Configure la sécurité Spring (JWT + OAuth2)
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
////	    private CustomOAuth2 customOAuth2UserService;
//
//	    @Autowired
////	    private OAuthSuccessHandler oauthSuccessHandler;
//
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	        .cors(Customizer.withDefaults()) 
//	            .csrf().disable()
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/api/**").permitAll()  
//	                .requestMatchers("/api/admin/**","/api/grades/**","/api/enseignants/**","/api/comptes/**").hasRole("ADMIN")  
//	                .requestMatchers("/api/enseignant/**").hasRole("Enseignant") 
//	                .anyRequest().authenticated()  
////	            )
////	            .oauth2Login(oauth -> oauth
////	                .userInfoEndpoint(user -> user.userService(customOAuth2UserService))
////	                .successHandler(oauthSuccessHandler)
//	            )
//	            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
//	        return http.build();
//	    }
//}
//
////🧠 Ce que ça fait :
////
////On configure Spring Security pour :
////
////Autoriser /api/auth/** librement.
////
////Bloquer tout autre accès sans authentification.
////
////Activer la connexion OAuth2 avec un service custom + redirection personnalisée après login.
////
