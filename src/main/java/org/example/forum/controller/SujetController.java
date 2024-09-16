package org.example.forum.controller;

import jakarta.validation.Valid;
import org.example.forum.entity.Message;
import org.example.forum.entity.Sujet;
import org.example.forum.entity.Utilisateur;
import org.example.forum.service.AuthService;
import org.example.forum.service.MessageService;
import org.example.forum.service.SujetService;
import org.example.forum.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page; // Pagination

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SujetController {

    // ========== Propriétés ==========

    private final SujetService sujetService;
    private final MessageService messageService;
    private final UtilisateurService utilisateurService;
    private final AuthService authService;


    // ========== Controller ==========

    public SujetController(SujetService sujetService, MessageService messageService, UtilisateurService utilisateurService, AuthService authService) {
        this.sujetService = sujetService;
        this.messageService = messageService;
        this.utilisateurService = utilisateurService;
        this.authService = authService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    @GetMapping("/sujets")
    public String listSujets(Model model) {

        List<Sujet> sujets = sujetService.getAllSujets();

        model.addAttribute("sujets", sujets);

        model.addAttribute("totalSujets", sujetService.countAllSujets()); // Compter nb sujets
        model.addAttribute("totalMessages", messageService.countAllMessages()); // Compter nb messages

        model.addAttribute("title", "Forum - Liste de sujets"); // Pour le title de la page

        // Pour chaque sujet, on récupère le dernier message et son auteur
        for (Sujet s : sujets) {
            Message lastMessage = messageService.getLastMessageBySujetId(s.getId());
            if (lastMessage != null) {
                s.setLastMessage(lastMessage);
            }
        }

        return "sujets";
    }



//    // Méthode pour lister les sujets avec pagination (Ne marche pas)
//    @GetMapping("/sujets")
//    public String listeSujets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
//
//        Page<Sujet> sujets = sujetService.getSujetsPagine(page, size);
//
//        model.addAttribute("sujets", sujets.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", sujets.getTotalPages());
//
//        return "sujets";
//    }





    @GetMapping("/sujet/{id}")
    public String getSujetById(@PathVariable("id") int id, Model model) {
        Sujet sujet = sujetService.getSujetById(id);
        if (sujet != null) {
            model.addAttribute("sujet", sujet);
            model.addAttribute("title", "Forum - Sujet : " + sujet.getTitre()); // Pour le title de la page
            return "sujet-details";
        }
        else {
            return "error/404";
        }
    }


    // ----- Create -----

    @GetMapping("/sujet/nouveau")
    public String showCreateSujetForm(Model model) {
        model.addAttribute("sujet", new Sujet());
        return "nouveau-sujet";
    }

    @PostMapping("/sujet/nouveau")
    public String createSujet(@Valid @ModelAttribute("sujet") Sujet sujet, BindingResult bindingResult, Model model) {

        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) {
            return "nouveau-sujet"; // Si erreurs de validation, retour au formulaire
        }

        // Le titre et le message sont sur 2 entités différentes (Sujet et Message), ce qui pose problème avec la validation. Problème non résolu. Je mets ceci pour éviter le bug et il n'y aura pas de message qui signale que le champ est obligatoire
        if (sujet.getMessage() == null || sujet.getMessage().trim().isEmpty()) {
            model.addAttribute("error", "Le message ne peut pas être vide.");
            return "nouveau-sujet"; // Revenir au formulaire
        }
        // --- ---

        Utilisateur utilisateur = utilisateurService.findCurrentUser(); // Récupérer l'utilisateur connecté en session

        if (utilisateur == null) {
            model.addAttribute("error", "Utilisateur non connecté.");
            return "nouveau-sujet";
        }

        sujet.setAuteur(utilisateur);
        sujetService.saveSujet(sujet); // 👈 Sauvegarder le sujet

        // Créer et associer le premier message au sujet
        Message message = new Message();
        message.setSujet(sujet);
        message.setUtilisateur(utilisateur);
        message.setMessage(sujet.getMessage()); // Utiliser le champ message du Sujet
        message.setDatetime(LocalDateTime.now());

        messageService.saveMessage(message); // 👈 Sauvegarder le message

        return "redirect:/sujet/" + sujet.getId();
    }


    // ----- Recherche -----

//    /**
//     * Rechercher de sujets par leur titre
//     */
//    @GetMapping("/recherche")
//    public String recherche(@RequestParam("searchTerm") String searchTerm, Model model) { // @RequestParam : pour lier les paramètres de requête ou les données de formulaire à un argument de méthode dans un contrôleur.
//
//        List<Sujet> sujets = sujetService.search(searchTerm);
//        model.addAttribute("sujets", sujets);
//
////        model.addAttribute("sujets", sujetService.search(searchTerm)); // Marche aussi
//
//        model.addAttribute("searchTerm", searchTerm);
//        model.addAttribute("title", "Recherche de sujets"); // Pour le title de la page
//
//        return "recherche"; // Renvoie vers la page de résultat
//    }


    /**
     * Rechercher de sujets par leur titre approximatif
     */
    @GetMapping("/recherche")
    public String recherche(@RequestParam("recherche") String recherche, @RequestParam(value = "tolerance", defaultValue = "2") int tolerance, Model model) {

        // Utilisation de la recherche approximative avec une tolérance donnée
        List<Sujet> sujets = sujetService.rechercheApproximativeByNom(recherche, tolerance);

        model.addAttribute("recherche", recherche);  // correction ici, passer le terme de recherche
        model.addAttribute("sujets", sujets);
        model.addAttribute("title", "Recherche de sujets"); // Pour le titre de la page

        return "recherche";
    }


}
