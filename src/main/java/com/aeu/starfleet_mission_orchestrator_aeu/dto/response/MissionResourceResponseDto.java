package com.aeu.starfleet_mission_orchestrator_aeu.dto.response;

import lombok.Data;

@Data
public class MissionResourceResponseDto {
    private Long resourceId;
    private String resourceName;
    private String unitOfmeasurement;
    private double quantityConsumed;

}
