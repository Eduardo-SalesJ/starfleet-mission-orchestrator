package com.aeu.starfleet_mission_orchestrator_aeu.dto.request;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Rank;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Specialty;
import lombok.Data;

@Data
public class FleetMemberRequestDto {
    private String name;
    private String username;
    private String password;
    private Rank ranks;
    private Specialty specialty;
    private int experience;
}
