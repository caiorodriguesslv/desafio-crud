package com.example.desafio.demo.controllers;

import com.example.desafio.demo.dto.CustomError;
import com.example.desafio.demo.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status =  HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), e.getMessage(),status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
