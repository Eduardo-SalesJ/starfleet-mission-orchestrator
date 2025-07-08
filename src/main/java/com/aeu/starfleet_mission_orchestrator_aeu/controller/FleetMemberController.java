package com.aeu.starfleet_mission_orchestrator_aeu.controller;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.FleetMemberRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.FleetMemberResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.Rank;
import com.aeu.starfleet_mission_orchestrator_aeu.repository.FleetMemberRepository;
import com.aeu.starfleet_mission_orchestrator_aeu.service.FleetMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class FleetMemberController {
    private final FleetMemberService fleetMemberService;

    public FleetMemberController(FleetMemberService fleetMemberService) {
        this.fleetMemberService = fleetMemberService;
    }
    @PostMapping
    public ResponseEntity<FleetMemberResponseDto> createFleetMember(@Valid @RequestBody
                                                                        FleetMemberRequestDto requestDto){
        FleetMemberResponseDto response = fleetMemberService.createFleetMember(requestDto);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FleetMemberResponseDto>> getAllFleetMembers(){
        List<FleetMemberResponseDto> responseDto = fleetMemberService.getAllFleetMembers();
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FleetMemberResponseDto>getFleetMemberById(@PathVariable Long id){
        FleetMemberResponseDto responseDto = fleetMemberService.getFleetMemberById(id);
        return ResponseEntity.ok(responseDto);
    }

    //Endpoint responsável por promover ou rebaixar a patente do membro
@PutMapping("/{id}/rank")
    public ResponseEntity<FleetMemberResponseDto>updateFleetMemberRank(
            @PathVariable Long id,
            @RequestParam Rank newRank){
        FleetMemberResponseDto responseDto = fleetMemberService.updateFleetMemberRank(id, newRank);
        return ResponseEntity.ok(responseDto);
    }
}
