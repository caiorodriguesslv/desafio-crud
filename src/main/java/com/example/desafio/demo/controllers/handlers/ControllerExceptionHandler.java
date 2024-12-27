package com.example.desafio.demo.controllers.handlers;

import com.example.desafio.demo.dto.CustomError;
import com.example.desafio.demo.dto.ValidationError;
import com.example.desafio.demo.services.exceptions.ResourceNotFoundException;
import com.example.desafio.demo.services.exceptions.DataIntegrityViolationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomError> dataIntegrityException(DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), e.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status =  HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(),"Dados invalidos",status.value(), request.getRequestURI());
        for(FieldError f:  e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<CustomError> handleDatabaseIntegrityViolation(org.springframework.dao.DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError err = new CustomError(
                Instant.now(),
                "Recurso já existe ou violação de integridade.",
                status.value(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}

