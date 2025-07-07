package com.aeu.starfleet_mission_orchestrator_aeu.dto.error;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validation;

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path, Map<String, String> validation) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.validation = validation;
    }
}
