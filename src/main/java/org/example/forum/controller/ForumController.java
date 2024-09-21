package org.example.forum.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.forum.entity.Message;
import org.example.forum.service.MessageService;
import org.example.forum.service.SujetService;
import org.example.forum.service.UtilisateurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.mindrot.jbcrypt.BCrypt; // Utiliser le hachage de mot de passe BCrypt


@Controller
public class ForumController {

    // ========== Propriétés ==========

    private final SujetService sujetService;
    private final MessageService messageService;
    private final UtilisateurService utilisateurService;

    // ========== Constructeur ==========

    /**
     * Constructeur
     */
    public ForumController(SujetService sujetService, MessageService messageService, UtilisateurService utilisateurService) {
        this.sujetService = sujetService;
        this.messageService = messageService;
        this.utilisateurService = utilisateurService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Page d'Accueil
     */
    @GetMapping("/") // URL : http://localhost:8080/
    public String home(Model model) {

//        if (authService.isLogged()) {
//            model.addAttribute("isLogged", true);
//        }
//        else {
//            model.addAttribute("isLogged", false);
//        }

        // Pareil que ci-dessus en plus court :
//        model.addAttribute("isLogged", authService.isLogged()); // Encore mieux, il suffit de mettre ceci une seule fois dans ModelController avec annotation @ControllerAdvice

        model.addAttribute("title", "Forum Manga - Bienvenue sur notre forum !"); // Pour le title de la page

        long nombreTotalMembres = utilisateurService.getNombreTotalUtilisateurs();
        model.addAttribute("nbTotalMembres", nombreTotalMembres);

        model.addAttribute("totalSujets", sujetService.countAllSujets()); // Compter nb sujets
        model.addAttribute("totalMessages", messageService.countAllMessages()); // Compter nb messages

        Message latestMessage = messageService.getLatestMessage();
        if (latestMessage != null) {
            model.addAttribute("dernierMessage", latestMessage);
        }
        else {
            model.addAttribute("dernierMessage", null); // Sans ça, bug si aucun message
        }
        return "index"; // Renvoie le nom de la vue "index" pour la page d'accueil
    }


    // ----- Read - Test -----

    /**
     * Test de Cryptage BCrypt
     */
    @GetMapping("/test")
    public String testCryptage(Model model) {
        String password = "1234";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt()); // BCrypt.hashpw() pour hasher
        System.out.println("Mot de passe hashé : " + hashed); // Exemple: $2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze

        model.addAttribute("title", "Test Cryptage de mot de passe"); // Pour le title de la page
        model.addAttribute("info", hashed);
        return "test";
    }

    /**
     * Test de Décryptage BCrypt
     */
    @GetMapping("/test2")
    public String testDecryptage(Model model) {
        String pseudo = "haiou";
        String motdepasse = "1234";
        String hashedPassword = "$2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze"; // Hash de "1234"

        if (pseudo.equals("haiou") && BCrypt.checkpw(motdepasse, hashedPassword)) { // BCrypt.checkpw() pour vérifier hashage
            System.out.println("Pseudo et mot de passe correctes");
            model.addAttribute("info", "Pseudo et mot de passe correctes");
        }
        else {
            System.out.println("Erreur pseudo ou mot de passe");
            model.addAttribute("info", "Erreur pseudo ou mot de passe");
        }

        model.addAttribute("title", "Test Décryptage de mot de passe"); // Pour le title de la page
        return "test";
    }


}
