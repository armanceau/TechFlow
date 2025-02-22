package com.techflow.techflow.service;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.Role;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import com.techflow.techflow.application.dto.intervention.CreateIntervention;
import com.techflow.techflow.model.Intervention;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.domain.repository.InterventionRepository;
import com.techflow.techflow.domain.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.techflow.techflow.util.InterventionTestBuilder.uneIntervention;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class InterventionServiceUTest {
    @Mock
    private InterventionRepository interventionRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private InterventionService interventionService;

    @Test
    void test_creer_une_intervention() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur("Doe", "johndoe@exemple.fr", "p@ssword0", Role.USER);
        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.of(utilisateur));

        CreateIntervention createIntervention = new CreateIntervention(
                "description",
                TypeIntervention.DIAGNOSTIC,
                LocalDateTime.now(),
                120,
                StatutIntervention.EN_COURS,
                utilisateur.getId(),
                PrioriteIntervention.HAUTE,
                50.0f
        );

        Intervention intervention = uneIntervention().build();


        when(interventionRepository.save(any(Intervention.class))).thenReturn(intervention);

        // Act
        Intervention interventionCree = interventionService.create(createIntervention);

        // Assert
        assertNotNull(interventionCree);
        assertEquals("description", interventionCree.getDescription());
    }

    @Test
    void test_creer_une_intervention_avec_un_utilisateur_inexistant() {
        CreateIntervention createIntervention = new CreateIntervention(
                "description",
                TypeIntervention.DIAGNOSTIC,
                LocalDateTime.now(),
                120,
                StatutIntervention.EN_COURS,
                null,
                PrioriteIntervention.HAUTE,
                50.0f
        );

        assertThrows(RuntimeException.class, () -> interventionService.create(createIntervention));
    }
}