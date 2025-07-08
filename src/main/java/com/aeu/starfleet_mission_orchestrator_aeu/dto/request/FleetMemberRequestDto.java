package com.aeu.starfleet_mission_orchestrator_aeu.dto.request;

import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Rank;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Specialty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FleetMemberRequestDto {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;
    @Size(min = 4,max = 40, message = "The username must be between 4 and 40 characters long")
    private String username;
    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotNull
    private Rank ranks;
    @NotNull
    private Specialty specialty;
    @Min(value = 0)
    private int experience;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Rank getRanks() {
        return ranks;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public int getExperience() {
        return experience;
    }
}
