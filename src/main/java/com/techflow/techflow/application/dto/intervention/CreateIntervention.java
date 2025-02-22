package com.techflow.techflow.application.dto.intervention;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
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
    private String utilisateurId;

    @NotNull
    private PrioriteIntervention prioriteIntervention;

    @NotNull
    @Min(value = 0, message = "Le cout de l'intervention doit être supérieure à zéro")
    private Float cout;

    public CreateIntervention(String description, TypeIntervention typeIntervention, LocalDateTime date, Integer duree, StatutIntervention statutIntervention, String utilisateurId, PrioriteIntervention prioriteIntervention, Float cout) {
        this.description = description;
        this.typeIntervention = typeIntervention;
        this.date = LocalDateTime.now();
        this.duree = duree;
        this.statutIntervention = statutIntervention;
        this.utilisateurId = utilisateurId;
        this.prioriteIntervention = prioriteIntervention;
        this.cout = cout;
    }

}
