package org.example.forum.controller;

import org.example.forum.service.AuthService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;


@ControllerAdvice // Annotation globale qui permet de définir des méthodes partagées entre plusieurs contrôleurs
public class ModelController {

    // ========== Propriétés ==========

    private final AuthService authService;


    // ========== Controller ==========

    public ModelController(AuthService authService) {
        this.authService = authService;
    }


    // ========== Méthodes ==========

    @ModelAttribute
    public void addAuthenticationAttributes(Model model) {

        // Méthode exécutée automatiquement pour chaque requête de tout contrôleur (pour éviter de répéter cette ligne dans chaque méthode de controller) :
        model.addAttribute("isLogged", authService.isLogged());

    }

}
