package com.fiuza.fiap.order.core.dto.errors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlreadyExistExceptionDtoTest {
    @Test
    void test_alreadyExistExceptionDto_creation_and_accessors() {
        String expectedMessage = "Recurso já existe";
        int expectedStatus = 400;

        AlreadyExistExceptionDto dto = new AlreadyExistExceptionDto(expectedMessage, expectedStatus);

        assertEquals(expectedMessage, dto.message());
        assertEquals(expectedStatus, dto.status());
    }
}
