package com.aeu.starfleet_mission_orchestrator_aeu.controller;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.SpaceshipRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.SpaceshipResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.service.SpaceshipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spaceships")
public class SpaceshipController {
    private final SpaceshipService spaceshipService;

    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }
    @PostMapping
    public ResponseEntity<SpaceshipResponseDto> createSpaceship(@Valid @RequestBody SpaceshipRequestDto requestDto){
        SpaceshipResponseDto response = spaceshipService.createSpaceship(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
