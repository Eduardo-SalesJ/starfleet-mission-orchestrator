package com.aeu.starfleet_mission_orchestrator_aeu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spaceship_resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spaceship_id", nullable = false)
    private Spaceship spaceship;

    @ManyToOne(fetch = FetchType.EAGER) // Pode ser EAGER para facilitar o acesso ao nome do recurso
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private double quantity;
}

