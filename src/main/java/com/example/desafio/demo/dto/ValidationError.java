package com.example.desafio.demo.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Instant timestamp, String error, Integer status, String path) {
        super(timestamp, error, status, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String message){
        erros.add(new FieldMessage(fieldName, message));
    }
}
