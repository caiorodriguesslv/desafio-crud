package com.example.desafio.demo.dto;

import com.example.desafio.demo.entities.Client;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String name;

    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;

    @PositiveOrZero(message = "Renda não pode ser negativa")
    private Double income;

    @PastOrPresent(message = "Data de nascimento não pode ser futura")
    private LocalDate birthDate;

    @PositiveOrZero(message = "Número de filhos não pode ser negativo")
    private Integer children;

    public ClientDTO(){
    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity){
        this.id = entity.getId();
        this.cpf = entity.getCpf();
        this.name = entity.getName();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}