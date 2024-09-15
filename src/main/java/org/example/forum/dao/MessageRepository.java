package org.example.forum.dao;

import org.example.forum.entity.Message;
import org.springframework.data.domain.Page; // Pagination
import org.springframework.data.domain.Pageable; // Pagination
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Integer> {

    long count(); // Compter le nombre de messages

    @Query("SELECT m FROM Message m WHERE m.datetime = (SELECT MAX(m2.datetime) FROM Message m2)")
    Message findLatestMessage();

    List<Message> findAllByMessageContainingIgnoreCase(String message);

//    List<Message> findBySujetId(int sujetId);

//    @Query("SELECT m FROM Message m WHERE m.sujet.id = :sujetId")
    @Query(value = "SELECT m.* FROM message m WHERE m.sujet_id = :sujetId", nativeQuery = true)
    List<Message> findBySujetId(@Param("sujetId") int sujetId);

    Message findTopBySujetIdOrderByDatetimeDesc(int sujetId); // Méthode pour trouver le dernier message d'un sujet

    // ----- Pagination -----
    Page<Message> findBySujetId(int sujetId, Pageable pageable);
    // ----- -----


}