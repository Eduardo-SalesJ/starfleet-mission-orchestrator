package com.aeu.starfleet_mission_orchestrator_aeu.service;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.FleetMemberRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.FleetMemberResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.repository.FleetMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FleetMemberService {
    private final FleetMemberRepository fleetMemberRepository;

    public FleetMemberService(FleetMemberRepository fleetMemberRepository) {
        this.fleetMemberRepository = fleetMemberRepository;
//        throw new IllegalArgumentException("Username '" + requestDto.getUsername()+"' This username already exists.");
    }

    @Transactional
    public FleetMemberResponseDto createFleetMember(FleetMemberRequestDto requestDto){
        if(fleetMemberRepository.findByUsername(requestDto.getUsername()).isPresent()){

        }
    }
}
