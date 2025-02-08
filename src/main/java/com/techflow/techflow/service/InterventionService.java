package com.techflow.techflow.service;

import com.techflow.techflow.dto.intervention.CreateIntervention;
import com.techflow.techflow.model.Intervention;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.repository.InterventionRepository;
import com.techflow.techflow.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {
    private final InterventionRepository interventionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public InterventionService(InterventionRepository interventionRepository, UtilisateurRepository utilisateurRepository) {
        this.interventionRepository = interventionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Intervention> findAll() {
        return interventionRepository.findAll();
    }

    public Intervention findById(String uuid) {
        return interventionRepository.findOneByUuid(uuid).orElse(null);
    }

    //TODO
    public Intervention create(CreateIntervention intervention) {
        Utilisateur utilisateur = new Utilisateur(
                utilisateur.getId(),
                utilisateur.getFullName(),
                utilisateur.getEmail()
        );
        Intervention interventionACreer = new Intervention(
                intervention.getDescription(),
                intervention.getTypeIntervention(),
                intervention.getDate(),
                intervention.getDuree(),
                intervention.getStatutIntervention(),
                intervention.setUtilisateur(utilisateur),
                intervention.getPrioriteIntervention(),
                intervention.getCout()
        );
        return interventionRepository.save(interventionACreer);
    }
}
