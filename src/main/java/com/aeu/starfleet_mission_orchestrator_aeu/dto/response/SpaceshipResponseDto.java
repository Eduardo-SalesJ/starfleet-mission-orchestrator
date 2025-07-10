package com.aeu.starfleet_mission_orchestrator_aeu.dto.response;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipClass;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
import lombok.Data;

import java.util.List;

@Data
public class SpaceshipResponseDto {
    private Long id;
    private String name;
    private ShipClass shipClass;
    private  int crewCapacity;
    private double fuelConsumptionPerHour;
    private ShipStatus status;
    private List<FleetMemberResponseDto> currentCrew; // Lista de tripulantes a bordo.
    private List<SpaceshipResponseDto> resources; // Inventário de recursos.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public void setShipClass(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    public int getCrewCapacity() {
        return crewCapacity;
    }

    public void setCrewCapacity(int crewCapacity) {
        this.crewCapacity = crewCapacity;
    }

    public double getFuelConsumptionPerHour() {
        return fuelConsumptionPerHour;
    }

    public void setFuelConsumptionPerHour(double fuelConsumptionPerHour) {
        this.fuelConsumptionPerHour = fuelConsumptionPerHour;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public List<FleetMemberResponseDto> getCurrentCrew() {
        return currentCrew;
    }

    public void setCurrentCrew(List<FleetMemberResponseDto> currentCrew) {
        this.currentCrew = currentCrew;
    }

    public List<SpaceshipResponseDto> getResources() {
        return resources;
    }

    public void setResources(List<SpaceshipResponseDto> resources) {
        this.resources = resources;
    }
}
