package com.techflow.techflow.application.auth;

import com.techflow.techflow.constant.Role;
import com.techflow.techflow.application.dto.LoginDto;
import com.techflow.techflow.application.dto.RegisterDto;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.domain.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UtilisateurRepository utilisateurRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur signup(RegisterDto input) {

        if(utilisateurRepository.findByEmail(input.getEmail()).isPresent()){
            throw new RuntimeException("Email déjà utilisé.");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(input.getNom());
        utilisateur.setPrenom(input.getPrenom());
        utilisateur.setEmail(input.getEmail());
        utilisateur.setRole(Role.USER);
        utilisateur.setPassword(passwordEncoder.encode(input.getPassword()));

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur authenticate(LoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return utilisateurRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
