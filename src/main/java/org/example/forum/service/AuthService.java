package org.example.forum.service;

import jakarta.servlet.http.HttpSession;
import org.example.forum.dao.UtilisateurRepository;
import org.example.forum.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AuthService {

    // ========== Propriétés ==========

    private final UtilisateurRepository utilisateurRepository;

    private final HttpSession httpSession;

//    private final PasswordEncoder passwordEncoder;


    // ========== Constructeur ==========

    // Sans BCrypt :
    public AuthService(UtilisateurRepository utilisateurRepository, HttpSession httpSession) {
        this.utilisateurRepository = utilisateurRepository;
        this.httpSession = httpSession;
    }

    // Avec BCrypt :
//    public AuthService(UtilisateurRepository utilisateurRepository, HttpSession httpSession, PasswordEncoder passwordEncoder) {
//        this.utilisateurRepository = utilisateurRepository;
//        this.httpSession = httpSession;
//        this.passwordEncoder = passwordEncoder;
//    }


    // ========== Méthodes ==========

    // ----- Inscription -----

    // Sans BCrypt :
    public void register(Utilisateur utilisateur) {

        if (utilisateurRepository.findByUsername(utilisateur.getUsername()) != null) {
            throw new IllegalArgumentException("Pseudo déjà pris.");
        }

        utilisateurRepository.save(utilisateur);
    }

//    // Avec BCrypt :
//    public void register(Utilisateur utilisateur) {
//        if (utilisateurRepository.findByUsername(utilisateur.getUsername()) != null) {
//            throw new IllegalArgumentException("Pseudo déjà pris.");
//        }
//
//        String hashedPassword = passwordEncoder.encode(utilisateur.getPassword()); // Chiffrer le mot de passe avant de sauvegarder l'utilisateur
//        utilisateur.setPassword(hashedPassword);
//
//        utilisateurRepository.save(utilisateur);
//    }


    // ----- Connexion -----

    // Sans BCrypt :
    public boolean login(String username, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
            httpSession.setAttribute("username", utilisateur.getUsername());
            httpSession.setAttribute("login", "ok");
            return true;
        }
        return false;
    }


//    // Avec BCrypt :
//    public boolean login(String username, String password) {
//        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
//        if (utilisateur != null && passwordEncoder.matches(password, utilisateur.getPassword())) {
//            httpSession.setAttribute("username", utilisateur.getUsername());
//            httpSession.setAttribute("login", "ok");
//            return true;
//        }
//        return false;
//    }


    // ----- Vérifier connexion -----

    public boolean isLogged() {
        try {
            String isLogged = httpSession.getAttribute("login").toString();
            return isLogged.equals("ok");
        }
        catch (Exception ex) {
            return false;
        }
    }

    // ----- Déconnexion -----

    public void logout() {
        httpSession.invalidate();
    }


}
