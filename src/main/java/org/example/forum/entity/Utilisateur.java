package org.example.forum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiquent que le champ id est la clé primaire et qu'il sera auto-généré par la base de données (génération automatique de l'ID)
    private int id;

    @NotBlank(message = "Pseudo obligatoire")
    @Size(min = 3, max = 100, message = "Entre 3 et 100 caractères")
    private String username;

    @NotBlank(message = "Mot de passe obligatoire")
    @Size(min = 4, max = 100, message = "Entre 4 et 100 caractères")
    private String password;


    // ---------- Relations ----------

    // Un utilisateur peut créer plusieurs sujets
    @OneToMany(mappedBy = "auteur")
    private List<Sujet> sujets;

    // Un utilisateur peut poster plusieurs messages
    @OneToMany(mappedBy = "utilisateur")
    private List<Message> messages;

}
