package com.aeu.starfleet_mission_orchestrator_aeu.config;

import com.aeu.starfleet_mission_orchestrator_aeu.dto.error.ErrorResponse;
import com.aeu.starfleet_mission_orchestrator_aeu.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Tratamento para ResourceNotFoundException (HTTP 404 Not Found)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    //Tratamento para validação dos DTOs
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Validation errors in the fields provided.",
                request.getDescription(false),
                errors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    //Tratamento para IllegalArgumentException - HTTP 400 Bad Request
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    //Tratamento exceções HTTP 500 - Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Error occurred on the server: " + ex.getMessage(),
                request.getDescription(false),
                null
        );
        logger.error("Unexpected error: \n " + ex.getMessage(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
