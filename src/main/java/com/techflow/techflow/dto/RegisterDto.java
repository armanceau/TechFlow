package com.techflow.techflow.dto;

import com.techflow.techflow.validator.StrongPassword;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterDto {
    @Size(min = 2, max = 30, message = "Le nom d'utilisateur doit comporter entre 2 et 30 caractères")
    private String nom;

    @Size(min = 2, max = 30, message = "Le prénom d'utilisateur doit comporter entre 2 et 30 caractères")
    private String prenom;

    private String username;

    private String email;

    @StrongPassword
    @Length(min = 8, max = 20, message = "Le mot de passe doit comporter entre 8 et 20 caractères")
    private String password;

    public RegisterDto(String nom, String prenom, String username, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}