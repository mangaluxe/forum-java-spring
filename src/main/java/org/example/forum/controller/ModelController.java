package org.example.forum.controller;

import org.example.forum.service.AuthService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;


@ControllerAdvice // Annotation globale qui permet d'appliquer des méthodes à tous les contrôleurs
public class ModelController {

    // ========== Propriétés ==========

    private final AuthService authService;


    // ========== Controller ==========

    public ModelController(AuthService authService) {
        this.authService = authService;
    }


    // ========== Méthodes ==========

    /**
     * Ajoute un attribut d'authentification ("isLogged") au modèle pour toutes les vues
     */
    @ModelAttribute // Une méthode marquée avec @ModelAttribute sera exécutée avant toutes les méthodes du contrôleur
    public void addAuthenticationAttributes(Model model) {

        // Méthode exécutée automatiquement pour chaque requête de tout contrôleur (pour éviter de répéter cette ligne dans chaque méthode de controller) :
        model.addAttribute("isLogged", authService.isLogged());

        /*
        ----- Utile pour appliquer ceci sur tous les controllers, car dans header.html, on a ces conditions : -----

        <div th:if="${isLogged}">
            <p>Bienvenue, vous êtes connecté !</p>
        </div>

        <div th:if="${!isLogged}">
            <a href="javascript:void(0)" rel="nofollow" class="icon-user open-connect"><span class="txt">Connexion</span></a>
        </div>

        ----- Sinon, on peut vérifier les sessions directement avec thymeleaf : -----

        <div th:if="${session.login != null}">
            (...)
        </div>

        <div th:if="${session.login == null}">
            <a href="javascript:void(0)" rel="nofollow" class="icon-user open-connect"><span class="txt">Connexion</span></a>
        </div>

        */

    }


    /**
     * Ajouter un token CSRF à toutes les vues
     */
    @ModelAttribute("csrf_token")
    public String addCsrfTokenToModel(HttpSession session) {

        String sessionCsrfToken = (String) session.getAttribute("csrf_token");

        if (sessionCsrfToken == null) { // Génère un token CSRF si ce n'est pas déjà présent dans la session
            SecureRandom secureRandom = new SecureRandom();
            byte[] token = new byte[32];
            secureRandom.nextBytes(token);
            sessionCsrfToken = Base64.getEncoder().encodeToString(token);

            session.setAttribute("csrf_token", sessionCsrfToken); // Stocke le token CSRF dans la session pour qu'on puisse utiliser dans le html
        }

        return sessionCsrfToken;
    }


}
