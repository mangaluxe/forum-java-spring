package org.example.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.forum.entity.Message;
import org.example.forum.service.MessageService;
import org.example.forum.service.SujetService;
import org.example.forum.service.UtilisateurService;
import org.springframework.web.bind.annotation.GetMapping;


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


}
