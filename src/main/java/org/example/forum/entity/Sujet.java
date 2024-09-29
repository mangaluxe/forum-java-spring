package org.example.forum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.forum.validation.MyValid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Entity
public class Sujet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiquent que le champ id est la clé primaire et qu'il sera auto-généré par la base de données (génération automatique de l'ID)
    private int id;

    @NotBlank(message = "Titre obligatoire")
    @MyValid(message = "Gros mots interdits") // Validation personnalisée
    private String titre;

    private LocalDateTime datetime = LocalDateTime.now(); // Datetime de création


    // ---------- Relations ----------

    // Relation avec l'auteur du sujet
    @ManyToOne
    @JoinColumn(name = "auteur_id") // Crée une colonne auteur_id sur la table 'sujet'
    private Utilisateur auteur;

    // Un sujet peut contenir plusieurs messages
    @OneToMany(mappedBy = "sujet", cascade = CascadeType.ALL, orphanRemoval = true) // DELETE ON CASCADE avec JPA. Remarque : on ne verra pas DELETE ON CASCADE dans l'interface graphique de phpMyAdmin ou PgAdmin, car CascadeType.ALL sont gérées au niveau de l'application
    private List<Message> messages;


    // ---------- Formatage de date ----------

    public String getDateTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
        return this.datetime != null ? this.datetime.format(formatter) : "";
    }

    /* Mettre dans le html :

    <span th:text="${sujet.getDateTimeFormatted()}"></span>
    */


    // ---------- @Transient : Données à ne pas persister en BDD : ----------

    // @Transient pour ne pas le persister en BDD. Cela permet de créer des propriétés calculées qui ne sont pas stockées en BDD, mais sont calculées au moment de l'exécution

    @Transient
    private String message; // Champ pour le premier message

    @Transient
    private Message lastMessage; // Champ pour le dernier message

    @Transient
    public int getNombreReponses() { // Calculer nombre de réponses
        return messages != null ? messages.size() : 0;
    }

}
