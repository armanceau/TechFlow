package com.techflow.techflow.util.model;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import com.techflow.techflow.model.Intervention;
import com.techflow.techflow.model.Utilisateur;

import java.time.LocalDateTime;

import static com.techflow.techflow.util.model.UtilisateurTestBuidler.unUtilisateur;

public class InterventionTestBuilder {

    private String description = "description";
    private TypeIntervention typeIntervention = TypeIntervention.DIAGNOSTIC;
    private LocalDateTime date = LocalDateTime.now();
    private Integer duree = 20;
    private StatutIntervention statutIntervention = StatutIntervention.EN_COURS;
    private Utilisateur utilisateurCree = unUtilisateur().build();
    private PrioriteIntervention prioriteIntervention = PrioriteIntervention.HAUTE;
    private Float cout = 50.0f;

    public static InterventionTestBuilder uneIntervention() {
        return new InterventionTestBuilder();
    }

    public InterventionTestBuilder avecDescription(String description) {
        this.description = description;
        return this;
    }

    public InterventionTestBuilder avecTypeIntervention(TypeIntervention typeIntervention) {
        this.typeIntervention = typeIntervention;
        return this;
    }

    public InterventionTestBuilder avecDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public InterventionTestBuilder avecDuree(Integer duree) {
        this.duree = duree;
        return this;
    }

    public InterventionTestBuilder avecStatutIntervention(StatutIntervention statutIntervention) {
        this.statutIntervention = statutIntervention;
        return this;
    }

    public InterventionTestBuilder avecUtilisateur(Utilisateur utilisateur) {
        this.utilisateurCree = utilisateur;
        return this;
    }

    public InterventionTestBuilder avecPrioriteIntervention(PrioriteIntervention prioriteIntervention) {
        this.prioriteIntervention = prioriteIntervention;
        return this;
    }

    public InterventionTestBuilder avecCout(Float cout) {
        this.cout = cout;
        return this;
    }

    public Intervention build() {
        return new Intervention(description, typeIntervention, date, duree, statutIntervention, utilisateurCree, prioriteIntervention, cout);
    }
}
