package org.example.forum.controller;

import org.example.forum.entity.Utilisateur;
import org.example.forum.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // ----- Inscription -----

    @RequestMapping("/registration")
    public String inscription(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "registration-form";
    }

    @PostMapping("/registration")
    public String inscriptionForm(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, RedirectAttributes redirectAttributes) {
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


    // ----- Connexion -----

    @RequestMapping("/login")
    public String connexion(Model model) {
        return "connexion-form";
    }

    @PostMapping("/login")
    public String connexionForm(@ModelAttribute("username") String username, @ModelAttribute("password") String password, RedirectAttributes redirectAttributes) {
        boolean connected = authService.login(username, password);
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


}
