package com.aeu.starfleet_mission_orchestrator_aeu.service;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.SpaceshipRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.FleetMemberResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.SpaceshipResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.exception.ResourceNotFoundException;
import com.aeu.starfleet_mission_orchestrator_aeu.model.Spaceship;
import com.aeu.starfleet_mission_orchestrator_aeu.repository.FleetMemberRepository;
import com.aeu.starfleet_mission_orchestrator_aeu.repository.SpaceshipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;
    private final FleetMemberRepository fleetMemberRepository;
    public SpaceshipService(SpaceshipRepository spaceshipRepository, FleetMemberRepository fleetMemberRepository, FleetMemberRepository fleetMemberRepository1){
        this.spaceshipRepository = spaceshipRepository;
        this.fleetMemberRepository = fleetMemberRepository;
    }
    @Transactional
    public SpaceshipResponseDto createSpaceship(SpaceshipRequestDto requestDto){
        if (spaceshipRepository.findByName(requestDto.getName()).isPresent()){
            throw new IllegalArgumentException("Spaceship name '" + requestDto.getName() + "'is already in use ");
        }
        Spaceship spaceship = new Spaceship();
        spaceship.setName(requestDto.getName());
        spaceship.setShipClass(requestDto.getShipClass());
        spaceship.setCrewCapacity(requestDto.getCrewCapacity());
        spaceship.setFuelConsumptionPerHour(requestDto.getFuelConsumptionPerHour());
        spaceship.setStatus(requestDto.getShipStatus());

        Spaceship savedSpaceship = spaceshipRepository.save(spaceship);
        return mapToResponseDto(savedSpaceship);
    }
    //Busca uma nave pelo ID
    @Transactional(readOnly = true)
    public SpaceshipResponseDto getSpaceshipById(Long id){
        Spaceship spaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spaceship not found with ID: " + id));
        return mapToResponseDto(spaceship);
    }
    //Listar todas as naves espaciáis
    @Transactional(readOnly = true)
    public List<SpaceshipResponseDto> getAllSpaceship(){
        return spaceshipRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    // Método responsável por fazer o mapeamento de MODEL para DTO
    private  SpaceshipResponseDto mapToResponseDto(Spaceship spaceship){
        SpaceshipResponseDto dto = new SpaceshipResponseDto();
        dto.setId(spaceship.getId());
        dto.setName(spaceship.getName());
        dto.setShipClass(spaceship.getShipClass());
        dto.setCrewCapacity(spaceship.getCrewCapacity());
        dto.setFuelConsumptionPerHour(spaceship.getFuelConsumptionPerHour());
        dto.setStatus(spaceship.getStatus());

        //Lógica para mapear a tripulação atual
        if (spaceship.getCurrentCrew() != null){
            dto.setCurrentCrew(spaceship.getCurrentCrew().stream()
                    .map(fleetMember ->{
                        FleetMemberResponseDto memberDto = new FleetMemberResponseDto();
                        memberDto.setId(fleetMember.getId());
                        memberDto.setUsername(fleetMember.getName());
                        memberDto.setUsername(fleetMember.getUsername());
                        memberDto.setRanks(fleetMember.getRanks());
                        memberDto.setSpecialty(fleetMember.getSpecialty());
                        return memberDto;
                        })
                    .collect(Collectors.toList()));
        }

        return dto;

    }
}
