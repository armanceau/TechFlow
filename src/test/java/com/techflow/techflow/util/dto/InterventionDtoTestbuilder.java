package com.techflow.techflow.util.dto;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import com.techflow.techflow.dto.InterventionDto;

import java.time.LocalDateTime;

import static com.techflow.techflow.util.model.UtilisateurTestBuidler.unUtilisateur;

public class InterventionDtoTestbuilder {
    private String description = "description";
    private TypeIntervention typeIntervention = TypeIntervention.DIAGNOSTIC;
    private LocalDateTime date = LocalDateTime.now();
    private Integer duree = 20;
    private StatutIntervention statutIntervention = StatutIntervention.EN_COURS;
    private String utilisateurId = unUtilisateur().build().getId();
    private PrioriteIntervention prioriteIntervention = PrioriteIntervention.HAUTE;
    private Float cout = 50.0f;

    public static InterventionDtoTestbuilder uneInterventionDto() {
        return new InterventionDtoTestbuilder();
    }

    public InterventionDtoTestbuilder avecDescription(String description) {
        this.description = description;
        return this;
    }

    public InterventionDtoTestbuilder avecTypeIntervention(TypeIntervention typeIntervention) {
        this.typeIntervention = typeIntervention;
        return this;
    }

    public InterventionDtoTestbuilder avecDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public InterventionDtoTestbuilder avecDuree(Integer duree) {
        this.duree = duree;
        return this;
    }

    public InterventionDtoTestbuilder avecStatutIntervention(StatutIntervention statutIntervention) {
        this.statutIntervention = statutIntervention;
        return this;
    }

    public InterventionDtoTestbuilder avecUtilisateur(String utilisateurId) {
        this.utilisateurId = utilisateurId;
        return this;
    }

    public InterventionDtoTestbuilder avecPrioriteIntervention(PrioriteIntervention prioriteIntervention) {
        this.prioriteIntervention = prioriteIntervention;
        return this;
    }

    public InterventionDtoTestbuilder avecCout(Float cout) {
        this.cout = cout;
        return this;
    }

    public InterventionDto build() {
        return new InterventionDto(description, typeIntervention, date, duree, statutIntervention, utilisateurId, prioriteIntervention, cout);
    }
}
