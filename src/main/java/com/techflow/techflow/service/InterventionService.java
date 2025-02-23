package com.techflow.techflow.service;

import com.techflow.techflow.dto.InterventionDto;
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

    public Intervention create(InterventionDto interventionDto) {
        Utilisateur utilisateur = utilisateurRepository.findById(interventionDto.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Intervention intervention = new Intervention(
                interventionDto.getDescription(),
                interventionDto.getTypeIntervention(),
                interventionDto.getDate(),
                interventionDto.getDuree(),
                interventionDto.getStatutIntervention(),
                utilisateur,
                interventionDto.getPrioriteIntervention(),
                interventionDto.getCout()
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
    public boolean update(String uuid, InterventionDto interventionDto) {
        Optional<Intervention> optionalIntervention = interventionRepository.findOneByUuid(uuid);
        if (optionalIntervention.isPresent()) {
            Intervention intervention = optionalIntervention.get();

            Utilisateur utilisateur = utilisateurRepository.findById(interventionDto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            intervention.setDescription(interventionDto.getDescription());
            intervention.setTypeIntervention(interventionDto.getTypeIntervention());
            intervention.setDate(interventionDto.getDate());
            intervention.setDuree(interventionDto.getDuree());
            intervention.setStatutIntervention(interventionDto.getStatutIntervention());
            intervention.setUtilisateurAssigne(utilisateur);
            intervention.setPriorite(interventionDto.getPrioriteIntervention());
            intervention.setCout(interventionDto.getCout());

            interventionRepository.save(intervention);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updatePartielle(String uuid, InterventionDto interventionDto) {
        Optional<Intervention> optionalIntervention = interventionRepository.findOneByUuid(uuid);
        if (optionalIntervention.isPresent()) {
            Intervention intervention = optionalIntervention.get();

            if (interventionDto.getDescription() != null) {
                intervention.setDescription(interventionDto.getDescription());
            }
            if (interventionDto.getTypeIntervention() != null) {
                intervention.setTypeIntervention(interventionDto.getTypeIntervention());
            }
            if (interventionDto.getDate() != null) {
                intervention.setDate(interventionDto.getDate());
            }
            if (interventionDto.getDuree() != null) {
                intervention.setDuree(interventionDto.getDuree());
            }
            if (interventionDto.getStatutIntervention() != null) {
                intervention.setStatutIntervention(interventionDto.getStatutIntervention());
            }
            if (interventionDto.getUtilisateurId() != null) {
                Utilisateur utilisateur = utilisateurRepository.findById(interventionDto.getUtilisateurId())
                        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                intervention.setUtilisateurAssigne(utilisateur);
            }
            if (interventionDto.getPrioriteIntervention() != null) {
                intervention.setPriorite(interventionDto.getPrioriteIntervention());
            }
            if (interventionDto.getCout() != 0) {
                intervention.setCout(interventionDto.getCout());
            }

            interventionRepository.save(intervention);
            return true;
        }
        return false;
    }
}
