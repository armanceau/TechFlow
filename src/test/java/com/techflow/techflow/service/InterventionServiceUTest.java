package com.techflow.techflow.service;

import com.techflow.techflow.constant.PrioriteIntervention;
import com.techflow.techflow.constant.StatutIntervention;
import com.techflow.techflow.constant.TypeIntervention;
import com.techflow.techflow.dto.InterventionDto;
import com.techflow.techflow.model.Intervention;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.repository.InterventionRepository;
import com.techflow.techflow.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.techflow.techflow.util.InterventionTestBuilder.uneIntervention;
import static com.techflow.techflow.util.UtilisateurTestBuidler.unUtilisateur;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        Utilisateur utilisateur = unUtilisateur().build();
        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.of(utilisateur));

        InterventionDto interventionDto = new InterventionDto(
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
        Intervention interventionCree = interventionService.create(interventionDto);

        // Assert
        assertNotNull(interventionCree);
        assertEquals("description", interventionCree.getDescription());
    }

    @Test
    void test_creer_une_intervention_avec_un_utilisateur_inexistant() {
        InterventionDto interventionDto = new InterventionDto(
                "description",
                TypeIntervention.DIAGNOSTIC,
                LocalDateTime.now(),
                120,
                StatutIntervention.EN_COURS,
                null,
                PrioriteIntervention.HAUTE,
                50.0f
        );

        assertThrows(RuntimeException.class, () -> interventionService.create(interventionDto));
    }
}