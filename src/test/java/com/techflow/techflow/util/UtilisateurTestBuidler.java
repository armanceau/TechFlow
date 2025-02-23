package com.techflow.techflow.util;

import com.techflow.techflow.constant.Role;
import com.techflow.techflow.model.Utilisateur;

public class UtilisateurTestBuidler {
    private String nom = "Doe";
    private String prenom = "John";
    private String username = "jdoe";
    private String email = "john@doe.com";
    private String password = "password123";
    private Role role = Role.USER ;

    public static UtilisateurTestBuidler unUtilisateur(){
        return new UtilisateurTestBuidler();
    }

    public UtilisateurTestBuidler avecNom(String nom){
        this.nom = nom;
        return this;
    }

    public UtilisateurTestBuidler avecPrenom(String prenom){
        this.prenom = prenom;
        return this;
    }

    public UtilisateurTestBuidler avecEmail(String email){
        this.email = email;
        return this;
    }

    public UtilisateurTestBuidler avecPassword(String password){
        this.password = password;
        return this;
    }

    public UtilisateurTestBuidler avecRole(Role role){
        this.role = role;
        return this;
    }

    public UtilisateurTestBuidler avecUsername(String username){
        this.username = username;
        return this;
    }

    public Utilisateur build(){
        return new Utilisateur(nom, prenom, username, email, password, role);
    }
}
