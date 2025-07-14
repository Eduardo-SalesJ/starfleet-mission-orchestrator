package com.aeu.starfleet_mission_orchestrator_aeu.controller;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.request.SpaceshipRequestDto;
import com.aeu.starfleet_mission_orchestrator_aeu.dto.response.SpaceshipResponseDto;
import com.aeu.starfleet_mission_orchestrator_aeu.model.enums.ShipStatus;
import com.aeu.starfleet_mission_orchestrator_aeu.service.FleetMemberService;
import com.aeu.starfleet_mission_orchestrator_aeu.service.SpaceshipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public ResponseEntity<SpaceshipResponseDto> getSpaceshipById(@PathVariable Long id){
        SpaceshipResponseDto response = spaceshipService.getSpaceshipById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<SpaceshipResponseDto>> getAllSpaceship(){
        List<SpaceshipResponseDto> responseDto = spaceshipService.getAllSpaceship();
        return ResponseEntity.ok(responseDto);
    }
    //Endpoint responsável por alterar o status de uma nave
    @PutMapping("/{id}/status")
    public ResponseEntity<SpaceshipResponseDto> updateSpaceshipStatus(
            @PathVariable Long id,
            @RequestParam ShipStatus newStatus){
        SpaceshipResponseDto responseDto = spaceshipService.updateSpaceship(id,newStatus);
        return ResponseEntity.ok(responseDto);
    }

}
