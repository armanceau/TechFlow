package com.techflow.techflow.application.dto.intervention;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import com.techflow.techflow.model.Utilisateur;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateIntervention {

    @Getter
    private final String description;

    @NotNull
    private final TypeIntervention typeIntervention;

    @NotNull
    private final LocalDateTime date;

    @NotNull
    @Min(value = 0, message = "La durée de l'intervention doit être supérieure à zéro")
    private Integer duree;

    @NotNull
    private StatutIntervention statutIntervention;

    @NotNull
    private Utilisateur utilisateurAssigne;

    @NotNull
    private PrioriteIntervention prioriteIntervention;

    @NotNull
    @Min(value = 0, message = "Le cout de l'intervention doit être supérieure à zéro")
    private Float cout;

    public UpdateIntervention(String description, TypeIntervention typeIntervention, LocalDateTime date,
                              Integer duree, StatutIntervention statutIntervention, PrioriteIntervention prioriteIntervention,
                              float cout, Utilisateur utilisateurAssigne) {
        this.description = description;
        this.typeIntervention = typeIntervention;
        this.date = date;
        this.duree = duree;
        this.statutIntervention = statutIntervention;
        this.prioriteIntervention = prioriteIntervention;
        this.cout = cout;
        this.utilisateurAssigne = utilisateurAssigne;
    }
}
