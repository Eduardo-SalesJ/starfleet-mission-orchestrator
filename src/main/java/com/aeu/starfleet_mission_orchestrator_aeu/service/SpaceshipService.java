package com.aeu.starfleet_mission_orchestrator_aeu.service;

import com.aeu.starfleet_mission_orchestrator_aeu.repository.FleetMemberRepository;
import com.aeu.starfleet_mission_orchestrator_aeu.repository.SpaceshipRepository;
import org.springframework.stereotype.Service;

@Service
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;
    private final FleetMemberRepository fleetMemberRepository;
    public SpaceshipService(SpaceshipRepository spaceshipRepository, FleetMemberRepository fleetMemberRepository, FleetMemberRepository fleetMemberRepository1){
        this.spaceshipRepository = spaceshipRepository;
        this.fleetMemberRepository = fleetMemberRepository;
    }
}
