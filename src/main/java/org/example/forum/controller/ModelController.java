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

    @ModelAttribute
    public void addAuthenticationAttributes(Model model) {

        // Méthode exécutée automatiquement pour chaque requête de tout contrôleur (pour éviter de répéter cette ligne dans chaque méthode de controller) :
        model.addAttribute("isLogged", authService.isLogged());

        /*
        ----- Utile d'appliquer ceci sur tous les controllers, car dans header.html, on a ces conditions : -----

        <div th:if="${isLogged == true}">
            (...)
        </div>

        <div th:if="${isLogged != true}">
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


    @ModelAttribute("csrfToken")
    public String addCsrfTokenToModel(HttpSession session) {

        String csrfToken = (String) session.getAttribute("csrfToken");

        if (csrfToken == null) { // Génère un token CSRF si ce n'est pas déjà présent dans la session
            SecureRandom secureRandom = new SecureRandom();
            byte[] token = new byte[32];
            secureRandom.nextBytes(token);
            csrfToken = Base64.getEncoder().encodeToString(token);

            session.setAttribute("csrfToken", csrfToken); // Pour qu'on puisse utiliser dans le html
        }

        return csrfToken;
    }

}
