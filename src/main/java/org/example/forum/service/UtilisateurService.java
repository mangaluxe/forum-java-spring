package org.example.forum.service;

import jakarta.servlet.http.HttpSession;
import org.example.forum.repository.UtilisateurRepository;
import org.example.forum.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurService {

    // ========== Propriétés ==========

    private final UtilisateurRepository utilisateurRepository;
    private final HttpSession httpSession;


    // ========== Constructeur ==========

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, HttpSession httpSession) {
        this.utilisateurRepository = utilisateurRepository;
        this.httpSession = httpSession;
    }


    // ========== Méthodes ==========

    /**
     * Obtenir l'utilisateur courant depuis la session
     * @return Utilisateur
     */
    public Utilisateur findCurrentUser() {
        String username = (String) httpSession.getAttribute("username");
        if (username != null) {
            return utilisateurRepository.findByUsername(username);
        }
        return null;
    }


    /**
     * Compter nb d'utilisateurs
     * @return long
     */
    public long getNombreTotalUtilisateurs() {
        return utilisateurRepository.count();
    }


    // Trouver un utilisateur par son nom d'utilisateur
    public Utilisateur findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

}
