package com.Mahdi.hadiji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PfeApplication {

	public static void main(String[] args) {
		// Démarrer Spring Boot
		SpringApplication.run(PfeApplication.class, args);

		// Afficher un mot de passe encodé (pour test uniquement)
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoded = encoder.encode("12345678"); 
		String encoded2 = encoder.encode("pass@123");
		System.out.println("Mot de passe encodé : " + encoded);
		System.out.println("Mot de passe encodé 2 : " + encoded2);
	}
}

