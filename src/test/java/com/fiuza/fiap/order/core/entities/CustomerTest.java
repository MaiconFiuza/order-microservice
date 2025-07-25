package com.fiuza.fiap.order.core.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerTest {

    @Test
    void testConstructorWithArgs() {
        UUID id = UUID.randomUUID();
        String cpf = "12345678900";
        String name = "João da Silva";
        LocalDate birthdate = LocalDate.of(1990, 5, 20);
        String login = "joaosilva";
        String password = "senha123";
        String address = "Rua Teste, 123";

        Customer customer = new Customer(id, cpf, name, birthdate, login, password, address);

        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getCpf()).isEqualTo(cpf);
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getBirthdate()).isEqualTo(birthdate);
        assertThat(customer.getLogin()).isEqualTo(login);
        assertThat(customer.getPassword()).isEqualTo(password);
        assertThat(customer.getAddress()).isEqualTo(address);
    }

    @Test
    void testSettersAndGetters() {
        Customer customer = new Customer();

        UUID id = UUID.randomUUID();
        String cpf = "98765432100";
        String name = "Maria Souza";
        LocalDate birthdate = LocalDate.of(1985, 8, 15);
        String login = "mariasouza";
        String password = "senha456";
        String address = "Av. Exemplo, 456";

        customer.setId(id);
        customer.setCpf(cpf);
        customer.setName(name);
        customer.setBirthdate(birthdate);
        customer.setLogin(login);
        customer.setPassword(password);
        customer.setAddress(address);

        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getCpf()).isEqualTo(cpf);
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getBirthdate()).isEqualTo(birthdate);
        assertThat(customer.getLogin()).isEqualTo(login);
        assertThat(customer.getPassword()).isEqualTo(password);
        assertThat(customer.getAddress()).isEqualTo(address);
    }
}