package com.techflow.techflow.domain.repository;

import com.techflow.techflow.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterventionRepository extends JpaRepository<Intervention, Integer> {
    List<Intervention> findAll();

    Optional<Intervention> findOneByUuid(String uuid);

    @SuppressWarnings({ "null", "unchecked" })
    Intervention save(Intervention intervention);
}
