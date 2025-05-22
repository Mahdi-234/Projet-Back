package com.Mahdi.hadiji.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// t7otek te9bel mn front bareka b heki el adresse 
//Access-Control-Allow-Origin
//Autorise le frontend à appeler l’API
// Ce fichier dit : "j’autorise React (localhost:5173) à appeler mon backend".

@Configuration
public class CorsConfig {

    @Bean
    
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // toutes les routes autorisées
                        .allowedOrigins("http://localhost:5173") // origine frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
