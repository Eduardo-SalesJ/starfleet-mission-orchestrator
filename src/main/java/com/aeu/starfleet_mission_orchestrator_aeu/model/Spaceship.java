package com.aeu.starfleet_mission_orchestrator_aeu.model;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipClass;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spaceships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spaceship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipClass shipClass;
    @Column(nullable = false)
    private int crewCapacity;
    @Column(nullable = false)
    private double fuelConsumptionPerHour;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipStatus status;

    @OneToMany(mappedBy = "currentSpaceship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FleetMember> currentCrew = new ArrayList<>(); // Tripulação atualmente a bordo

    @OneToMany(mappedBy = "spaceship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipResource> resources = new ArrayList<>(); // Inventário de recursos da nave

    @OneToMany(mappedBy = "assignedSpaceship", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>(); // Missões que esta nave participou

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

    public List<FleetMember> getCurrentCrew() {
        return currentCrew;
    }

    public void setCurrentCrew(List<FleetMember> currentCrew) {
        this.currentCrew = currentCrew;
    }

    public List<ShipResource> getResources() {
        return resources;
    }

    public void setResources(List<ShipResource> resources) {
        this.resources = resources;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }
}
