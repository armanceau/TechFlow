package com.techflow.techflow.model;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name="priorite_intervention", nullable = false)
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
}
