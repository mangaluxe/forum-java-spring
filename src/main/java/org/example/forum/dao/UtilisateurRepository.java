package org.example.forum.dao;

import org.example.forum.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    long count();  // Méthode de JpaRepository pour compter le nombre total d'entrées (ici le nb d'utilisateurs)

    Utilisateur findByUsername(String username); // Méthode pour trouver utilisateur par son nom d'utilisateur

}
