package com.techflow.techflow.model;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "numero", unique = true, nullable = false)
    private String uuid;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="type_intervention", nullable = false)
    private TypeIntervention typeIntervention;

    @Column(name="date", nullable = false)
    private LocalDateTime date;

    @Column(name="duree_estimee", nullable = false)
    private Integer duree;

    @Enumerated(EnumType.STRING)
    @Column(name="statut_intervention", nullable = false)
    private StatutIntervention statutIntervention;

    @ManyToOne
    @JoinColumn(name="utilisateur_assigne_id")
    private Utilisateur utilisateurAssigne;

    @Enumerated(EnumType.STRING)
    @Column(name="priorite", nullable = false)
    private PrioriteIntervention priorite;

    @Column(name="cout",nullable = false)
    private float cout;

    public Intervention(String description, TypeIntervention typeIntervention, LocalDateTime date, Integer duree, StatutIntervention statutIntervention, Utilisateur utilisateurAssigne, PrioriteIntervention priorite, float cout) {
        this.description = description;
        this.typeIntervention = typeIntervention;
        this.date = date;
        this.duree = duree;
        this.statutIntervention = statutIntervention;
        this.utilisateurAssigne = utilisateurAssigne;
        this.priorite = priorite;
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

    public Utilisateur getUtilisateurAssigne() {
        return utilisateurAssigne;
    }

    public void setUtilisateurAssigne(Utilisateur utilisateurAssigne) {
        this.utilisateurAssigne = utilisateurAssigne;
    }

    public PrioriteIntervention getPriorite() {
        return priorite;
    }

    public void setPriorite(PrioriteIntervention priorite) {
        this.priorite = priorite;
    }

    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }
}
