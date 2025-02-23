package com.techflow.techflow.util.dto.RegisterDto;

import com.techflow.techflow.dto.RegisterDto;

public class RegisterDtoTestBuidler {
    private String nom = "Doe";
    private String prenom = "John";
    private String username = "jdoe";
    private String email = "john@doe.com";
    private String password = "password123";

    public static RegisterDtoTestBuidler unRegisterDto() {
        return new RegisterDtoTestBuidler();
    }

    public RegisterDtoTestBuidler avecNom(String nom){
        this.nom = nom;
        return this;
    }

    public RegisterDtoTestBuidler avecPrenom(String prenom){
        this.prenom = prenom;
        return this;
    }

    public RegisterDtoTestBuidler avecEmail(String email){
        this.email = email;
        return this;
    }

    public RegisterDtoTestBuidler avecPassword(String password){
        this.password = password;
        return this;
    }

    public RegisterDtoTestBuidler avecUsername(String username){
        this.username = username;
        return this;
    }

    public RegisterDto build(){
        return new RegisterDto(nom, prenom, username, email, password);
    }
}
