package com.techflow.techflow.service;

import com.techflow.techflow.constant.Role;
import com.techflow.techflow.dto.LoginDto;
import com.techflow.techflow.dto.RegisterDto;
import com.techflow.techflow.model.Utilisateur;
import com.techflow.techflow.repository.UtilisateurRepository;
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
        Utilisateur user = new Utilisateur();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return utilisateurRepository.save(user);
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
