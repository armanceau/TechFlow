package com.techflow.techflow.controller;

import com.techflow.techflow.dto.InterventionDto;
import com.techflow.techflow.model.Intervention;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.service.InterventionService;
import com.techflow.techflow.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static com.techflow.techflow.util.dto.InterventionDtoTestbuilder.uneInterventionDto;
import static com.techflow.techflow.util.model.InterventionTestBuilder.uneIntervention;
import static com.techflow.techflow.util.model.UtilisateurTestBuidler.unUtilisateur;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InterventionControllerTest {

    @Mock
    private InterventionService interventionService;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private InterventionController interventionController;

    private Intervention intervention1;
    private Intervention intervention2;
    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        utilisateur = unUtilisateur().build();

        intervention1 = uneIntervention().build();

        intervention2 = uneIntervention().avecDescription("description2").build();
    }

    @Test
    void testFindAll() {
        // Arrange
        when(interventionService.findAll()).thenReturn(Arrays.asList(intervention1, intervention2));

        // Act
        ResponseEntity<List<Intervention>> response = interventionController.findAll();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testFindOneById_Found() {
        // Arrange
        String uuid = "uuid1";
        when(interventionService.findByUuid(uuid)).thenReturn(intervention1);

        // Act
        ResponseEntity<Intervention> response = interventionController.findOneById(uuid);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("description", response.getBody().getDescription());
    }

    @Test
    void testFindOneById_NotFound() {
        // Arrange
        String uuid = "uuidNotFound";
        when(interventionService.findByUuid(uuid)).thenReturn(null);

        // Act
        ResponseEntity<Intervention> response = interventionController.findOneById(uuid);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testSave() {
        // Arrange
        InterventionDto interventionDto = uneInterventionDto().build();
        interventionDto.setDescription("New Intervention");
        interventionDto.setUtilisateurId("123");
        when(utilisateurService.findById("123")).thenReturn(utilisateur);
        when(interventionService.create(interventionDto)).thenReturn(intervention1);

        // Act
        ResponseEntity<Intervention> response = interventionController.save(interventionDto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("description", response.getBody().getDescription());
    }

    @Test
    void testSave_BadRequest() {
        // Arrange
        InterventionDto interventionDto = uneInterventionDto().build();
        interventionDto.setDescription("New Intervention");
        interventionDto.setUtilisateurId("999");
        when(utilisateurService.findById("999")).thenReturn(null);

        // Act
        ResponseEntity<Intervention> response = interventionController.save(interventionDto);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testDelete_Found() {
        // Arrange
        String uuid = "uuid1";
        when(interventionService.delete(uuid)).thenReturn(true);

        // Act
        ResponseEntity<?> response = interventionController.delete(uuid);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        String uuid = "uuidNotFound";
        when(interventionService.delete(uuid)).thenReturn(false);

        // Act
        ResponseEntity<?> response = interventionController.delete(uuid);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testPut_Update_Found() {
        // Arrange
        String uuid = "uuid1";
        InterventionDto interventionDto = uneInterventionDto().build();
        interventionDto.setDescription("Updated Intervention");
        when(interventionService.update(uuid, interventionDto)).thenReturn(true);

        // Act
        ResponseEntity<?> response = interventionController.mettreAJourTotalement(uuid, interventionDto);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testPut_Update_NotFound() {
        // Arrange
        String uuid = "uuidNotFound";
        InterventionDto interventionDto = uneInterventionDto().build();
        interventionDto.setDescription("Updated Intervention");
        when(interventionService.update(uuid, interventionDto)).thenReturn(false);

        // Act
        ResponseEntity<?> response = interventionController.mettreAJourTotalement(uuid, interventionDto);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }
}
