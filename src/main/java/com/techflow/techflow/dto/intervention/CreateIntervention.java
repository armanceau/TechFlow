package com.techflow.techflow.dto.intervention;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import com.techflow.techflow.model.Utilisateur;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateIntervention {
    private String description;

    @NotNull
    private TypeIntervention typeIntervention;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Min(value = 0, message = "La durée de l'intervention doit être supérieure à zéro")
    private Integer duree;

    @NotNull
    private StatutIntervention statutIntervention;

    @NotNull
    private Utilisateur utilisateur;

    @NotNull
    private PrioriteIntervention prioriteIntervention;

    @NotNull
    @Min(value = 0, message = "Le cout de l'intervention doit être supérieure à zéro")
    private Float cout;

    public CreateIntervention(String description, TypeIntervention typeIntervention, LocalDateTime date, Integer duree, StatutIntervention statutIntervention, Utilisateur utilisateur, PrioriteIntervention prioriteIntervention, Float cout) {
        this.description = description;
        this.typeIntervention = typeIntervention;
        this.date = LocalDateTime.now();
        this.duree = duree;
        this.statutIntervention = statutIntervention;
        this.utilisateur = utilisateur;
        this.prioriteIntervention = prioriteIntervention;
        this.cout = cout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeIntervention getTypeIntervention() {
        return typeIntervention;
    }

    public void setTypeIntervention(TypeIntervention typeIntervention) {
        this.typeIntervention = typeIntervention;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public StatutIntervention getStatutIntervention() {
        return statutIntervention;
    }

    public void setStatutIntervention(StatutIntervention statutIntervention) {
        this.statutIntervention = statutIntervention;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public PrioriteIntervention getPrioriteIntervention() {
        return prioriteIntervention;
    }

    public void setPrioriteIntervention(PrioriteIntervention prioriteIntervention) {
        this.prioriteIntervention = prioriteIntervention;
    }

    public Float getCout() {
        return cout;
    }

    public void setCout(Float cout) {
        this.cout = cout;
    }
}
