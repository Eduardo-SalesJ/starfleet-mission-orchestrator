package com.aeu.starfleet_mission_orchestrator_aeu.model;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipClass;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
}
