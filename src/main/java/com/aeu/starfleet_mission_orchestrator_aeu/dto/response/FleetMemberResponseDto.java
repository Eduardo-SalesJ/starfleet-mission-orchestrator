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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
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

    public Long getCurrentSpaceshipId() {
        return currentSpaceshipId;
    }

    public String getCurrentSpaceshipName() {
        return currentSpaceshipName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRanks(Rank ranks) {
        this.ranks = ranks;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setCurrentSpaceshipId(Long currentSpaceshipId) {
        this.currentSpaceshipId = currentSpaceshipId;
    }

    public void setCurrentSpaceshipName(String currentSpaceshipName) {
        this.currentSpaceshipName = currentSpaceshipName;
    }
}
