package com.aeu.starfleet_mission_orchestrator_aeu.service;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.SpaceshipRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.FleetMemberResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.SpaceshipResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.exception.ResourceNotFoundException;
import com.aeu.starfleet_mission_orchestrator_aeu.model.FleetMember;
import com.aeu.starfleet_mission_orchestrator_aeu.model.Spaceship;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
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

    @Transactional
    public SpaceshipResponseDto updateSpaceship(Long id, ShipStatus newStatus){
        Spaceship spaceship = spaceshipRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Spaceship not found with ID: " + id));
        spaceship.setStatus(newStatus);
        Spaceship updatedSpaceship = spaceshipRepository.save(spaceship);
        return mapToResponseDto(updatedSpaceship);

    }
    // Método responsável por atribuir tripulação a nave espacial
    @Transactional
    public SpaceshipResponseDto assignCrewToSpaceship(Long spaceshipId, List<Long> crewMemberIds) {
        Spaceship spaceship = spaceshipRepository.findById(spaceshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Spaceship not found with ID: " + spaceshipId));
        if (spaceship.getStatus() == ShipStatus.ON_MISSION) {
            throw new IllegalArgumentException("It is not possible to assign crew to a ship on a mission.");
        }
        List<FleetMember> newCrew = fleetMemberRepository.findAllById(crewMemberIds);

        if (newCrew.size() != crewMemberIds.size()) {
            throw new IllegalArgumentException("One or more crew member IDs are invalid.");
        }
        if (newCrew.size() > spaceship.getCrewCapacity()) {
            throw new IllegalArgumentException("The number of crew exceeds the ship's capacity (" + spaceship.getCrewCapacity() + ").");
        }

        //Limpa a tripulação atual e atribui a nova
        spaceship.getCurrentCrew().forEach(member -> member.setCurrentSpaceship(null)); // Desvincula os antigos.
        spaceship.getCurrentCrew().clear(); // Faz a limpeza da lista.

        newCrew.forEach(member -> {
            if(member.getCurrentSpaceship() != null && !member.getCurrentSpaceship().equals(spaceship)){
                throw new IllegalArgumentException("Member '" + member.getName() + "' is already assigned to another ship." );
            }
            member.setCurrentSpaceship(spaceship);
            spaceship.getCurrentCrew().add(member);
                });
        Spaceship updatedSpaceship = spaceshipRepository.save(spaceship);
        fleetMemberRepository.saveAll(newCrew); // Salva os membros com a nova atribuição de nave
        return  mapToResponseDto(updatedSpaceship);
    }

    public SpaceshipResponseDto updateSpaceshipStatus(Long id, ShipStatus newStatus ){
        Spaceship spaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spaceship not found with ID: " + id));
        spaceship.setStatus(newStatus);
        Spaceship updatedSpaceship = spaceshipRepository.save(spaceship);
        return mapToResponseDto(updatedSpaceship);
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
