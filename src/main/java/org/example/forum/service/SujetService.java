package org.example.forum.service;

import org.example.forum.dao.SujetRepository;
import org.example.forum.entity.Sujet;
import org.springframework.stereotype.Service;
import org.example.forum.util.Levenshtein; // Ma propre méthode distance de Levenshtein
import org.springframework.data.domain.Page; // Pagination
import org.springframework.data.domain.PageRequest; // Pagination
import org.springframework.data.domain.Pageable; // Pagination

import java.util.List;


@Service
public class SujetService {

    // ========== Propriétés ==========

    private final SujetRepository sujetRepository;


    // ========== Constructeur ==========

    public SujetService(SujetRepository sujetRepository) {
        this.sujetRepository = sujetRepository;
    }


    // ========== Méthodes ==========

    // Méthode pour obtenir tous les sujets
    public List<Sujet> getAllSujets() {
        return sujetRepository.findAll();
    }

    // Méthode pour obtenir un sujet par ID
    public Sujet getSujetById(int id) {
        return sujetRepository.findById(id).orElse(null);
    }

    public Sujet findById(int id) {
        return sujetRepository.findById(id).orElse(null);
    }

    // Sauvegarder un nouveau sujet
    public Sujet saveSujet(Sujet sujet) {
        return sujetRepository.save(sujet); // Retourner le sujet sauvegardé
    }

    // ----- Compter nombre de sujets -----

    public long countAllSujets() {
        return sujetRepository.count();
    }

    // ----- Recherche -----

    /**
     * Recherche de sujets par leur titre
     */
    public List<Sujet> search(String searchTerm) {
        return sujetRepository.findByTitreContainingIgnoreCase(searchTerm);
    }


    /**
     * Recherche de sujets par leur titre approximatif avec la distance de Levenshtein
     */
    public List<Sujet> rechercheApproximativeByNom(String recherche, int tolerance) {
        List<Sujet> allSujets = sujetRepository.findAll(); // Récupérer tous les sujets

        return allSujets.stream()
                .filter(s -> {
                    String titre = s.getTitre().toLowerCase();
                    String rechercheLower = recherche.toLowerCase();

                    if (titre.length() < rechercheLower.length()) {
                        return false;  // Si la longueur du titre est inférieure à celle de la recherche
                    }

                    // Parcourir toutes les sous-chaînes du titre de la longueur de la recherche
                    for (int i = 0; i <= titre.length() - rechercheLower.length(); i++) {
                        String sousChaine = titre.substring(i, i + rechercheLower.length());
                        int distance = Levenshtein.calculLevenshteinDistance(rechercheLower, sousChaine);

                        if (distance <= tolerance) {
                            return true;  // Correspondance trouvée
                        }
                    }

                    return false;  // Pas de correspondance trouvée
                })
                .toList();
    }


    // ----- Pagination -----

    /**
     * Obtenir les sujets avec pagination
     */
    public Page<Sujet> getSujetsPagine(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sujetRepository.findAll(pageable);
    }

}
