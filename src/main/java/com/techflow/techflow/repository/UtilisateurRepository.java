package com.techflow.techflow.repository;

import com.techflow.techflow.model.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> {
    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findById(String id);
}
