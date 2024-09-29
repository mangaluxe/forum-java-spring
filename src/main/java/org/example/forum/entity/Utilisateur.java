package org.example.forum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.forum.validation.OnLogin; // Interface que j'ai créé pour obliger durant la connexion
import org.example.forum.validation.OnRegistration; // Interface que j'ai créé pour obliger durant l'inscription

import java.util.List;


@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiquent que le champ id est la clé primaire et qu'il sera auto-généré par la base de données (génération automatique de l'ID)
    private int id;

    @NotBlank(message = "Pseudo obligatoire", groups = {OnLogin.class, OnRegistration.class}) // OnLogin et OnRegistration : Obligatoire à l'inscription et connexion
    @Size(min = 3, max = 100, message = "Entre 3 et 100 caractères", groups = {OnLogin.class, OnRegistration.class})
    private String username;

    @NotBlank(message = "Mot de passe obligatoire", groups = {OnLogin.class, OnRegistration.class})
    @Size(min = 4, max = 100, message = "Entre 4 et 100 caractères", groups = {OnLogin.class, OnRegistration.class})
    private String password;

    @NotBlank(message = "Email obligatoire", groups = OnRegistration.class) // OnRegistration uniquement : Obligatoire uniquement lors de l'inscription
    @Email(message = "Email invalide", groups = OnRegistration.class)
    private String email;


    // ---------- Relations ----------

    // Un utilisateur peut créer plusieurs sujets
    @OneToMany(mappedBy = "auteur")
    private List<Sujet> sujets;

    // Un utilisateur peut poster plusieurs messages
    @OneToMany(mappedBy = "utilisateur")
    private List<Message> messages;

}
