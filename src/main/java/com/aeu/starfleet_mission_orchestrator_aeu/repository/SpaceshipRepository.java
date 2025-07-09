package com.aeu.starfleet_mission_orchestrator_aeu.repository;

import com.aeu.starfleet_mission_orchestrator_aeu.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
    Optional<Spaceship> findByName(String name);

}
