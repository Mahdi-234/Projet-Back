//package com.Mahdi.hadiji.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.Mahdi.hadiji.Entite.User;
//import com.Mahdi.hadiji.Repository.CompteRepository;
//
//@RestController
//@RequestMapping("/api/comptes")
//public class CompteController {
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private CompteRepository compteRepository;
//
//    @GetMapping
//    public List<User> getAllComptes() {
//        return compteRepository.findAll();
//    }
//    @PostMapping
//    public ResponseEntity<User> createCompte(@RequestBody User compte) {
//        // Hashage du mot de passe
//        String hashedPassword = passwordEncoder.encode(compte.getPassword());
//        compte.setPassword(hashedPassword);
//
//        User savedCompte = compteRepository.save(compte);
//        return ResponseEntity.ok(savedCompte);
//    }
//
//    
//}
