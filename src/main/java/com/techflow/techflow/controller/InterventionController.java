package com.techflow.techflow.controller;

import com.techflow.techflow.dto.InterventionDto;
import com.techflow.techflow.model.Intervention;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.service.InterventionService;
import com.techflow.techflow.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intervention")
@EnableMethodSecurity
public class InterventionController {

    private final InterventionService service;
    private final UtilisateurService utilisateurService;

    @Autowired
    public InterventionController(InterventionService service, UtilisateurService utilisateurService) {
        this.service = service;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<List<Intervention>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Intervention> findOneById(@PathVariable String uuid) {
        Intervention intervention = service.findByUuid(uuid);
        if (intervention != null) {
            return new ResponseEntity<>(intervention, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Intervention> save(@Valid @RequestBody InterventionDto interventionDto) {
        Utilisateur utilisateur = utilisateurService.findById(interventionDto.getUtilisateurId());
        if (utilisateur == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Intervention createdIntervention = service.create(interventionDto);
        return new ResponseEntity<>(createdIntervention, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        boolean isDeleted = service.delete(uuid);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> mettreAJourTotalement(
            @PathVariable String uuid,
            @RequestBody InterventionDto intervention) {
        boolean isUpdated = service.update(uuid, intervention);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}