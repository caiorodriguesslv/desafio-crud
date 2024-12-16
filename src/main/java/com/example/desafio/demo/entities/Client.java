package com.example.desafio.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message = "Nome não pode ser vázio!")
    private String name;
    @Column(unique = true)
    @Size(min = 11, max = 11, message = "O cpf deve conter 11 caracteres")
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Data de nascimento não pode ser futura")
    private LocalDate birthDate;
    private Integer children;

    public Client(){
    }

    public Client(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
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

    public @Size(min = 11, max = 11, message = "O cpf deve conter 11 caracteres") String getCpf() {
        return cpf;
    }

    public void setCpf(@Size(min = 11, max = 11, message = "O cpf deve conter 11 caracteres") String cpf) {
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
