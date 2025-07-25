package com.fiuza.fiap.order.core.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {
    private UUID id;
    private String cpf;
    private String name;
    private LocalDate birthdate;
    private String login;
    private String password;
    private String address;

    public Customer(UUID id, String cpf, String name, LocalDate birthdate, String login, String password, String address) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public Customer() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
