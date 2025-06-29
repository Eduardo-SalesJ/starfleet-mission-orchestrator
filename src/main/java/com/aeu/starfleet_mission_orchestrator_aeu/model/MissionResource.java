package com.aeu.starfleet_mission_orchestrator_aeu.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "mission_resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.EAGER) // Pode ser EAGER para facilitar o acesso ao nome do recurso
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private double quantityConsumed;
}
