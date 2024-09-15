package org.example.forum.controller;

import jakarta.validation.Valid;
import org.example.forum.entity.Message;
import org.example.forum.entity.Sujet;
import org.example.forum.entity.Utilisateur;
import org.example.forum.service.MessageService;
import org.example.forum.service.SujetService;
import org.example.forum.service.UtilisateurService;
import org.springframework.data.domain.Page; // Pagination
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable; // Pagination
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault; // Pagination
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class MessageController {

    // ========== Propriétés ==========

    private final MessageService messageService;
    private final SujetService sujetService;
    private final UtilisateurService utilisateurService;


    // ========== Controller ==========

    public MessageController(MessageService messageService, SujetService sujetService, UtilisateurService utilisateurService) {
        this.messageService = messageService;
        this.sujetService = sujetService;
        this.utilisateurService = utilisateurService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Affiche tous les messages d'un sujet (avec pagination)
     */
    @GetMapping("/sujet/{id}/messages")
    public String getMessagesBySujet(@PathVariable int id,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size,
                                     Model model) {

        List<Message> messages = messageService.getMessagesBySujetId(id);

        model.addAttribute("messages", messages);

        // --- Pagination ---
        Sujet sujet = sujetService.getSujetById(id);
        if (sujet == null) {
            return "redirect:/sujets";
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("datetime").descending());
        Page<Message> messagesPage = messageService.getMessagesBySujetId(id, 5, 5);

        model.addAttribute("sujet", sujet);
        model.addAttribute("messagesPage", messagesPage);
        // --- ---

        return "sujets-details"; // Retourne cette vue (messages.html)
    }


//    /**
//     * Affiche tous les messages d'un sujet (sans pagination)
//     */
//    @GetMapping("/sujet/{id}/messages")
//    public String getMessagesBySujet(@PathVariable int id, Model model) {
//        List<Message> messages = messageService.getMessagesBySujetId(id);
//        model.addAttribute("messages", messages);
//        return "sujets-details"; // Retourne cette vue (messages.html)
//    }


    // ----- Create -----

    /**
     * Ajouter un message à un sujet existant
     */
    @PostMapping("/sujet/{id}/messages")
    public String addMessage(@PathVariable int id, @RequestParam("message") String messageContent, Model model) {

        // --- Validation de formulaire ---
//        if (bindingResult.hasErrors()) {
//            return "redirect:/sujet/" + id;
//        }
        // --- ---

        Sujet sujet = sujetService.getSujetById(id);
        if (sujet == null) {
            return "redirect:/sujets"; // Redirige si le sujet n'existe pas
        }

        Utilisateur utilisateur = utilisateurService.findCurrentUser();
        if (utilisateur == null) {
            return "redirect:/login"; // Redirige si l'utilisateur n'est pas connecté
        }

        Message message = new Message();
        message.setMessage(messageContent);
        message.setSujet(sujet);
        message.setUtilisateur(utilisateur);
        message.setDatetime(LocalDateTime.now());

        messageService.saveMessage(message);

        return "redirect:/sujet/" + id + "#m" + message.getId(); // Redirige vers la page du sujet après l'ajout du message
    }

}
