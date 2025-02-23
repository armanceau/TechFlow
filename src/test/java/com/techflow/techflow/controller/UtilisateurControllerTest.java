package com.techflow.techflow.controller;

import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Arrays;

import static com.techflow.techflow.util.UtilisateurTestBuidler.unUtilisateur;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    private Utilisateur utilisateur1;
    private Utilisateur utilisateur2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        utilisateur1 = unUtilisateur().build();
        utilisateur2 = unUtilisateur()
                .avecEmail("sophie.martin@example.com")
                .avecNom("Martin")
                .avecPrenom("Sophie")
                .build();
    }

    @Test
    void testFindAll() {
        // Arrange
        when(utilisateurService.findAll()).thenReturn(Arrays.asList(utilisateur1, utilisateur2));

        // Act
        ResponseEntity<List<Utilisateur>> response = utilisateurController.findAll();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testFindOneById_Found() {
        // Arrange
        String id = "123";
        when(utilisateurService.findById(id)).thenReturn(utilisateur1);

        // Act
        ResponseEntity<Utilisateur> response = utilisateurController.findOneById(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Doe", response.getBody().getNom());
    }

    @Test
    void testFindOneById_NotFound() {
        // Arrange
        String id = "999";
        when(utilisateurService.findById(id)).thenReturn(null);

        // Act
        ResponseEntity<Utilisateur> response = utilisateurController.findOneById(id);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
