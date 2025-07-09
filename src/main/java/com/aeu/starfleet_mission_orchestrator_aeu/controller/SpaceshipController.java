package com.aeu.starfleet_mission_orchestrator_aeu.controller;

import com.aeu.starfleet_mission_orchestrator_aeu.service.SpaceshipService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spaceships")
public class SpaceshipController {
    private final SpaceshipService spaceshipService;

    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }
}
