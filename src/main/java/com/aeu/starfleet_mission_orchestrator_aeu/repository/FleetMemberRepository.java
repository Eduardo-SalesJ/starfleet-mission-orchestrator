package com.aeu.starfleet_mission_orchestrator_aeu.repository;

import com.aeu.starfleet_mission_orchestrator_aeu.model.FleetMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FleetMemberRepository extends JpaRepository<FleetMember, Long> {

    // Método que busca um membro da frota
    Optional<FleetMember> findByUsername(String username);
}
