package com.fiuza.fiap.order.core.dto;

import com.fiuza.fiap.order.core.enums.Status;

import java.util.UUID;

public record PaymentDTO(
        UUID orderId,
        UUID customerId,
        Status status
) {
}
