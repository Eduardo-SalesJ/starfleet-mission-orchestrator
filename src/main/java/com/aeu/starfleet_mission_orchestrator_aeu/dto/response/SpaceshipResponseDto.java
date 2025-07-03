package com.aeu.starfleet_mission_orchestrator_aeu.dto.response;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
import lombok.Data;

import java.util.List;

@Data
public class SpaceshipResponseDto {
    private Long id;
    private String name;
    private String shipClass;
    private  int crewCapacity;
    private double fuelConsumptionPerHour;
    private ShipStatus status;
    private List<FleetMemberResponseDto> currentCrew; // Lista de tripulantes a bordo.
    private List<SpaceshipResponseDto> resources; // Inventário de recursos.



}
