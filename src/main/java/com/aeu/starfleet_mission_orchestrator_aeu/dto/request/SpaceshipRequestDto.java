package com.aeu.starfleet_mission_orchestrator_aeu.dto.request;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipClass;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SpaceshipRequestDto {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank
    private ShipClass shipClass;
    @Min(value = 1)
    private int crewCapacity;
    @Min(value = 0, message = "Fuel consumption cannot be negative")
    private double fuelConsumptionPerHour;
    @NotNull
    private ShipStatus shipStatus;

}
