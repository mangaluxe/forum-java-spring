package org.example.forum.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.forum.entity.Utilisateur;
import org.example.forum.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Message flash


@Controller
public class AuthController {

    // ========== Propriétés ==========

    private final AuthService authService;


    // ========== Constructeur ==========

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    // ========== Méthodes ==========

    // ----- Connexion -----

    /**
     * Formulaire pour connexion
     */
    @GetMapping("/login")
    public String connexionForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur()); // Pour maintenir les données soumises en cas d'erreur de validation. Mettre th:object="${utilisateur}" dans <form th:action="@{/login}" th:object="${utilisateur}" method="post">

        model.addAttribute("title", "Connexion sur le forum"); // Pour le title de la page

        return "connexion-form";
    }

    /**
     * Connexion
     */
    @PostMapping("/login")
    public String connexion(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, @RequestParam("csrfToken") String csrfToken, HttpSession session) {
        // --- Vérification du token CSRF ---
        String sessionCsrfToken = (String) session.getAttribute("csrfToken");

        if (sessionCsrfToken == null || !sessionCsrfToken.equals(csrfToken)) {
            model.addAttribute("error", "Erreur token CSRF !");
            return "connexion-form";
        }
        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) {
            return "connexion-form"; // Si erreurs de validation, retour au formulaire
        }
        // --- ---

        boolean connected = authService.login(utilisateur.getUsername(), utilisateur.getPassword());
        if (connected) {
            redirectAttributes.addFlashAttribute("successMessage", "Connexion réussie !"); // Message flash
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Identifiants incorrects !"); // Message flash
        return "redirect:/login";
    }


    // ----- Déconnexion -----

    @GetMapping("/logout")
    public String logout() {
        authService.logout();
        return "redirect:/";
    }


    // ----- Create : Inscription -----

    /**
     * Formulaire pour créer
     */
    @GetMapping("/registration")
    public String inscriptionForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur()); // Pour maintenir les données soumises en cas d'erreur de validation. Mettre th:object="${utilisateur}" dans <form th:action="@{/registration}" th:object="${utilisateur}" method="post">

        model.addAttribute("title", "Inscription sur le forum"); // Pour le title de la page

        return "registration-form";
    }

    /**
     * Créer
     */
    @PostMapping("/registration")
    public String inscription(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, @RequestParam("csrfToken") String csrfToken, HttpSession session) {
        // --- Vérification du token CSRF ---
        String sessionCsrfToken = (String) session.getAttribute("csrfToken");

        if (sessionCsrfToken == null || !sessionCsrfToken.equals(csrfToken)) {
            model.addAttribute("error", "Erreur token CSRF !");
            return "registration-form";
        }
        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) {
            return "registration-form"; // Si erreurs de validation, retour au formulaire
        }
        // --- ---

        try {
            authService.register(utilisateur);
            redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie ! Vous pouvez maintenant vous connecter."); // Message flash
            return "redirect:/";
        }
        catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registration-form";
        }
    }


}
