package com.aeu.starfleet_mission_orchestrator_aeu.dto.response;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Rank;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Specialty;
import lombok.Data;

@Data
public class FleetMemberResponseDto {
    private Long id;
    private String name;
    private String username;
    private Rank ranks;
    private Specialty specialty;
    private int experience;
    private Long currentSpaceshipId; // Id da nave atual.
    private String currentSpaceshipName; // Nome da nave atual.


}
