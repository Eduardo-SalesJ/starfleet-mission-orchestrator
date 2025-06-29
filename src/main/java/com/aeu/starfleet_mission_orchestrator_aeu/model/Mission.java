package com.aeu.starfleet_mission_orchestrator_aeu.model;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // Para descrições mais longas
    private String description;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate; // Pode ser nulo se a missão estiver em andamento

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_spaceship_id", nullable = false) // Nave atribuída
    private Spaceship assignedSpaceship;

    // Tripulação da missão
    @ManyToMany
    @JoinTable(
            name = "mission_crew",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "fleet_member_id")
    )
    private List<FleetMember> crew = new ArrayList<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionResource> resourcesConsumed = new ArrayList<>(); // Recursos consumidos na missão
}