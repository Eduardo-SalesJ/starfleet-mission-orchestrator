package com.aeu.starfleet_mission_orchestrator_aeu.dto.request;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.MissionStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MissionRequestDto {
    @NotBlank
    @Size(min = 5, max = 150)
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String destination;
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    private LocalDate endDate;
    @NotNull
    private MissionStatus status;
    @NotNull
    private Long assignedSpaceshipId;
    @NotNull
    @Size(min = 1)
    private List<Long> crewMemberIds;

}
