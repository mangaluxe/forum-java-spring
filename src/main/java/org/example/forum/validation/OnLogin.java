package org.example.forum.validation;

public interface OnLogin {
}

/*
La validation du champ email pose problème, car obligatoire pendant l'inscription, et pas demandé lors de la connexion. Une des solutions est de créer 2 interfaces :

validation/OnLogin.java :

    package org.example.forum.validation;
    public interface OnLogin {}

validation/OnRegistration.java :

    package org.example.forum.validation;
    public interface OnRegistration {}


----- Dans entity/Utilisateur : -----

import org.example.forum.validation.OnLogin;
import org.example.forum.validation.OnRegistration;

@NotBlank(message = "Pseudo obligatoire", groups = {OnLogin.class, OnRegistration.class})
@Size(min = 3, max = 100, message = "Entre 3 et 100 caractères", groups = {OnLogin.class, OnRegistration.class})
private String username;

@NotBlank(message = "Mot de passe obligatoire", groups = {OnLogin.class, OnRegistration.class})
@Size(min = 4, max = 100, message = "Entre 4 et 100 caractères", groups = {OnLogin.class, OnRegistration.class})
private String password;

@NotBlank(message = "Email obligatoire", groups = OnRegistration.class)
@Email(message = "Email invalide", groups = OnRegistration.class)
private String email;

----- Dans AuthController.java : -----

import org.example.forum.validation.OnLogin;
import org.example.forum.validation.OnRegistration;
import org.springframework.validation.annotation.Validated;

@PostMapping("/login")
public String connexion(@Validated(OnLogin.class) @ModelAttribute("utilisateur") Utilisateur utilisateur,
                        BindingResult bindingResult,
                        Model model,
                        @RequestParam("csrfToken") String csrfToken,
                        HttpSession session) {


@PostMapping("/registration")
public String inscription(@Validated(OnRegistration.class) @ModelAttribute("utilisateur") Utilisateur utilisateur,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes,
                          @RequestParam("csrfToken") String csrfToken,
                          HttpSession session) {

*/