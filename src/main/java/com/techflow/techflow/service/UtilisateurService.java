package com.techflow.techflow.service;

import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur findById(String id) {
        return utilisateurRepository.findById(id).orElse(null);
    }
}
