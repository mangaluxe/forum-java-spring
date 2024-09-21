package org.example.forum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.forum.validation.MyValid;

import java.time.LocalDateTime;


@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiquent que le champ id est la clé primaire et qu'il sera auto-généré par la base de données (génération automatique de l'ID)
    private int id;

    @NotBlank(message = "Mettez un message")
    // @MyValid(message = "Gros mots interdits") // Validation personnalisée. Problème si on utilise, car le formulaire de "nouveau sujet" utilise 2 entités en même temps (Sujet et Message)
    @Column(columnDefinition = "text")
    private String message;

    private LocalDateTime datetime;


    // ---------- Relations ----------

    // Relation avec le sujet
    @ManyToOne
    @JoinColumn(name = "sujet_id") // Crée une colonne sujet_id sur la table 'message'
    private Sujet sujet;

    // Relation avec l'utilisateur qui poste
    @ManyToOne
    @JoinColumn(name = "user_id") // Crée une colonne user_id sur la table 'message'
    private Utilisateur utilisateur;

}
