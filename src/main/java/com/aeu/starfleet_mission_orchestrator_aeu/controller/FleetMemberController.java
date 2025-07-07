package com.aeu.starfleet_mission_orchestrator_aeu.controller;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.FleetMemberRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.FleetMemberResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.service.FleetMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class FleetMemberController {
    private final FleetMemberService fleetMemberService;

    public FleetMemberController(FleetMemberService fleetMemberService) {
        this.fleetMemberService = fleetMemberService;
    }
    @PostMapping
    public ResponseEntity<FleetMemberResponseDto> createFleetMember(@Valid FleetMemberRequestDto requestDto){
        FleetMemberResponseDto response = fleetMemberService.createFleetMember(requestDto);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
