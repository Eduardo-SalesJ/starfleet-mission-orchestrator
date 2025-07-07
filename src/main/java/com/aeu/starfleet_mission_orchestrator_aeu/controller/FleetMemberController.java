package com.aeu.starfleet_mission_orchestrator_aeu.controller;

import com.aeu.starfleet_mission_orchestrator_aeu.service.FleetMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class FleetMemberController {
    private final FleetMemberService fleetMemberService;

    public FleetMemberController(FleetMemberService fleetMemberService) {
        this.fleetMemberService = fleetMemberService;
    }

}
