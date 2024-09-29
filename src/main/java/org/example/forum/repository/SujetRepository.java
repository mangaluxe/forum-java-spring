package org.example.forum.repository;

import org.example.forum.entity.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page; // Pagination
import org.springframework.data.domain.Pageable; // Pagination

import java.util.List;


public interface SujetRepository extends JpaRepository<Sujet, Integer> {

    long count(); // Compter le nombre de sujets

    List<Sujet> findAllByTitreContainingIgnoreCase(String titre);

//    @Query("SELECT s FROM Sujet s JOIN FETCH s.auteur") // @Query pour utiliser son propre SQL ou JPQL
    @Query(value = "SELECT s.*, a.* FROM sujet s JOIN auteur a ON s.auteur_id = a.id", nativeQuery = true)
    List<Sujet> findAllWithAuteurs();


    // ----- Recherche -----

    List<Sujet> findAll();

    List<Sujet> findByTitreContainingIgnoreCase(String titre);

    // ----- Pagination -----

    Page<Sujet> findAll(Pageable pageable); // Obtenir tous les sujets avec pagination

}