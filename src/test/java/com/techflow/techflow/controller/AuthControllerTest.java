package com.techflow.techflow.controller;

import com.techflow.techflow.dto.LoginDto;
import com.techflow.techflow.dto.LoginResponse;
import com.techflow.techflow.dto.RegisterDto;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.security.JwtService;
import com.techflow.techflow.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static com.techflow.techflow.util.model.UtilisateurTestBuidler.unUtilisateur;
import static com.techflow.techflow.util.dto.RegisterDtoTestBuidler.unRegisterDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        utilisateur = unUtilisateur().build();
    }

    @Test
    void testRegister_Success() {
        // Arrange
        RegisterDto registerDto = unRegisterDto().build();

        when(authService.signup(registerDto)).thenReturn(utilisateur);

        // Act
        ResponseEntity<Utilisateur> response = authController.register(registerDto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Doe", response.getBody().getNom());
    }

    @Test
    void testAuthenticate_Success() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("john@doe.com");
        loginDto.setPassword("password123");

        when(authService.authenticate(loginDto)).thenReturn(utilisateur);
        when(jwtService.generateToken(utilisateur)).thenReturn("fake-jwt-token");
        when(jwtService.getExpirationTime()).thenReturn(3600L);

        // Act
        ResponseEntity<LoginResponse> response = authController.authenticate(loginDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("fake-jwt-token", response.getBody().getToken());
        assertEquals(3600L, response.getBody().getExpiresIn());
    }
}
