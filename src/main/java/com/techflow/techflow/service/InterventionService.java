package com.techflow.techflow.service;

import com.techflow.techflow.dto.intervention.CreateIntervention;
import com.techflow.techflow.dto.intervention.UpdateIntervention;
import com.techflow.techflow.model.Intervention;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.repository.InterventionRepository;
import com.techflow.techflow.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Intervention findByUuid(String uuid) {
        return interventionRepository.findOneByUuid(uuid).orElse(null);
    }

    public Intervention create(CreateIntervention createIntervention) {
        Utilisateur utilisateur = utilisateurRepository.findById(createIntervention.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Intervention intervention = new Intervention(
                createIntervention.getDescription(),
                createIntervention.getTypeIntervention(),
                createIntervention.getDate(),
                createIntervention.getDuree(),
                createIntervention.getStatutIntervention(),
                utilisateur,
                createIntervention.getPrioriteIntervention(),
                createIntervention.getCout()
        );

        return interventionRepository.save(intervention);
    }


    public boolean delete(String interventionUuid) {
        Optional<Intervention> intervention = interventionRepository.findOneByUuid(interventionUuid);
        if (intervention.isPresent()) {
            interventionRepository.delete(intervention.get());
            return true;
        }
        return false;
    }

    @Transactional
    public boolean update(String uuid, UpdateIntervention updateIntervention) {
        Optional<Intervention> optionalIntervention = interventionRepository.findOneByUuid(uuid);
        if (optionalIntervention.isPresent()) {
            Intervention intervention = optionalIntervention.get();

            Utilisateur utilisateur = utilisateurRepository.findById(updateIntervention.getUtilisateurAssigne().getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            intervention.setDescription(updateIntervention.getDescription());
            intervention.setTypeIntervention(updateIntervention.getTypeIntervention());
            intervention.setDate(updateIntervention.getDate());
            intervention.setDuree(updateIntervention.getDuree());
            intervention.setStatutIntervention(updateIntervention.getStatutIntervention());
            intervention.setUtilisateurAssigne(utilisateur);
            intervention.setPriorite(updateIntervention.getPrioriteIntervention());
            intervention.setCout(updateIntervention.getCout());

            interventionRepository.save(intervention);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updatePartielle(String uuid, UpdateIntervention updateIntervention) {
        Optional<Intervention> optionalIntervention = interventionRepository.findOneByUuid(uuid);
        if (optionalIntervention.isPresent()) {
            Intervention intervention = optionalIntervention.get();

            if (updateIntervention.getDescription() != null) {
                intervention.setDescription(updateIntervention.getDescription());
            }
            if (updateIntervention.getTypeIntervention() != null) {
                intervention.setTypeIntervention(updateIntervention.getTypeIntervention());
            }
            if (updateIntervention.getDate() != null) {
                intervention.setDate(updateIntervention.getDate());
            }
            if (updateIntervention.getDuree() != null) {
                intervention.setDuree(updateIntervention.getDuree());
            }
            if (updateIntervention.getStatutIntervention() != null) {
                intervention.setStatutIntervention(updateIntervention.getStatutIntervention());
            }
            if (updateIntervention.getUtilisateurAssigne() != null) {
                Utilisateur utilisateur = utilisateurRepository.findById(updateIntervention.getUtilisateurAssigne().getId())
                        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                intervention.setUtilisateurAssigne(utilisateur);
            }
            if (updateIntervention.getPrioriteIntervention() != null) {
                intervention.setPriorite(updateIntervention.getPrioriteIntervention());
            }
            if (updateIntervention.getCout() != 0) {
                intervention.setCout(updateIntervention.getCout());
            }

            interventionRepository.save(intervention);
            return true;
        }
        return false;
    }
}
