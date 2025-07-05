package com.aeu.starfleet_mission_orchestrator_aeu.service;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.FleetMemberRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.FleetMemberResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.model.FleetMember;
import com.aeu.starfleet_mission_orchestrator_aeu.repository.FleetMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FleetMemberService {
    private final FleetMemberRepository fleetMemberRepository;

    public FleetMemberService(FleetMemberRepository fleetMemberRepository) {
        this.fleetMemberRepository = fleetMemberRepository;
//        throw new IllegalArgumentException("Username '" + requestDto.getUsername()+"' This username already exists.");
    }

    @Transactional
    public FleetMemberResponseDto createFleetMember(FleetMemberRequestDto requestDto){
        //Verifica se o username já existe
        if(fleetMemberRepository.findByUsername(requestDto.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username '" + requestDto.getUsername()+ "'This name is already in use.");
        }
        FleetMember fleetMember = new FleetMember();
        fleetMember.setName(requestDto.getName());
        fleetMember.setUsername(requestDto.getUsername());
        fleetMember.setPassword(requestDto.getPassword());
        fleetMember.setRanks(requestDto.getRanks());
        fleetMember.setSpecialty(requestDto.getSpecialty());
        fleetMember.setExperience(requestDto.getExperience());

        FleetMember savedMember = fleetMemberRepository.save(fleetMember);
        return mapToResponseDto(savedMember);
    }
    //Busca um membro da frota através do ID
    @Transactional(readOnly = true)
    public FleetMemberResponseDto getFleetMemberById(Long id){
        Optional<FleetMember> memberOptional = fleetMemberRepository.findById(id);
        if(memberOptional.isEmpty()){
            throw new IllegalArgumentException("FleetMember not found with ID: " + id);

        }
        return mapToResponseDto(memberOptional.get());
    }
    //Metodo responsável por Listar todos os membros da frota
    @Transactional(readOnly = true)
    public List<FleetMemberResponseDto> getAllFleetMembers(){
        return fleetMemberRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    //Metodo responsável por mapear uma entidade FleetMember para FleetMemberResponseDto
    private FleetMemberResponseDto mapToResponseDto(FleetMember fleetMember){
        FleetMemberResponseDto dto = new FleetMemberResponseDto();
        dto.setId(fleetMember.getId());
        dto.setName(fleetMember.getName());
        dto.setUsername(fleetMember.getUsername());
        dto.setRanks(fleetMember.getRanks());
        dto.setSpecialty(fleetMember.getSpecialty());
        dto.setExperience(fleetMember.getExperience());

        if (fleetMember.getCurrentSpaceship() != null){
            dto.setCurrentSpaceshipId(fleetMember.getCurrentSpaceship().getId());
            dto.setCurrentSpaceshipName(fleetMember.getCurrentSpaceship().getName());
        }
        return dto;

    }
}
