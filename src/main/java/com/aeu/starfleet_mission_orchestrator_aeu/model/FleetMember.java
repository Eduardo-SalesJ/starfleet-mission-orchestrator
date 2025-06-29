package com.aeu.starfleet_mission_orchestrator_aeu.model;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Rank;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Specialty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fleet_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FleetMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rank rank;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty specialty;
    @Column(nullable = false)
    private int experience;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_spaceship_id") // ID da nave atual
    private Spaceship currentSpaceship;

    // Relacionamento ManyToMany com Mission para as missões que o membro participou
    @ManyToMany(mappedBy = "crew")
    private List<Mission> missions;

}
