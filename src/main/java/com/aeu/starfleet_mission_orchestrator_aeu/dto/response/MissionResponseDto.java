package com.aeu.starfleet_mission_orchestrator_aeu.dto.response;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.MissionStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MissionResponseDto {

    private Long id;
    private String title;
    private String description;
    private String destination;
    private LocalDate startDate;

    private LocalDate endDate;
    private MissionStatus status;
    private Long assignedSpaceshipId;
    private String assignedSpaceshipName; // nave atribuida;
    private List<FleetMemberResponseDto> crew; //Tripulação da missão.
    private List<MissionResourceResponseDto> resourcesConsumed; //


}
