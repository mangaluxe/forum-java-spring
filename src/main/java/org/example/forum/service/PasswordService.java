//package org.example.forum.service;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class PasswordService {
//
//    private final PasswordEncoder passwordEncoder;
//
//    public PasswordService() {
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }
//
//    // Méthode pour encoder le mot de passe
//    public String encodePassword(String password) {
//        return passwordEncoder.encode(password);
//    }
//
//    // Méthode pour vérifier le mot de passe
//    public boolean matches(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//}
