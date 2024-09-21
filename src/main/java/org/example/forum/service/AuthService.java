package org.example.forum.service;

import jakarta.servlet.http.HttpSession;
import org.example.forum.dao.UtilisateurRepository;
import org.example.forum.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt; // Utiliser le hachage de mot de passe BCrypt


@Service
public class AuthService {

    // ========== Propriétés ==========

    private final UtilisateurRepository utilisateurRepository;

    private final HttpSession httpSession;


    // ========== Constructeur ==========

    public AuthService(UtilisateurRepository utilisateurRepository, HttpSession httpSession) {
        this.utilisateurRepository = utilisateurRepository;
        this.httpSession = httpSession;
    }


    // ========== Méthodes ==========

    // ----- Inscription -----

//    public void register(Utilisateur utilisateur) {
//        if (utilisateurRepository.findByUsername(utilisateur.getUsername()) != null) {
//            throw new IllegalArgumentException("Pseudo déjà pris.");
//        }
//        utilisateurRepository.save(utilisateur);
//    }

    /**
     * Inscrire (avec hash de mot de passe)
     */
    public void register(Utilisateur utilisateur) {
        if (utilisateurRepository.findByUsername(utilisateur.getUsername()) != null) {
            throw new IllegalArgumentException("Pseudo déjà pris.");
        }

        String hashedPassword = BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt()); // Hasher le mot de passe
        utilisateur.setPassword(hashedPassword); // Remplacer le mot de passe par sa version hashée

        utilisateurRepository.save(utilisateur);
    }



    // ----- Connexion -----

//    public boolean login(String username, String password) {
//        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
//
//        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
//            httpSession.setAttribute("username", utilisateur.getUsername());
//            httpSession.setAttribute("login", "ok");
//            return true;
//        }
//        return false;
//    }

    /**
     * Connexion (avec vérification de hash de mot de passe)
     */
    public boolean login(String username, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);

        if (utilisateur != null && BCrypt.checkpw(password, utilisateur.getPassword())) {
            httpSession.setAttribute("username", utilisateur.getUsername());
            httpSession.setAttribute("login", "ok");
            return true;
        }

        return false;
    }



    // ----- Vérifier connexion -----

    /**
     * Vérifier qu'on est connecté
     */
    public boolean isLogged() {
        try {
            String isLogged = httpSession.getAttribute("login").toString();
            return isLogged.equals("ok"); // Retourne true si isLogged == "ok"
        }
        catch (Exception ex) {
            return false;
        }
    }

    // ----- Déconnexion -----

    /**
     * Déconnexion
     */
    public void logout() {
        httpSession.invalidate();
    }


}
