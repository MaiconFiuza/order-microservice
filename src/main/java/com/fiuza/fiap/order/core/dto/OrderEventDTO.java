package com.fiuza.fiap.order.core.dto;

import java.util.List;
import java.util.UUID;

public record OrderEventDTO(
        UUID orderId,
        UUID customerId,
        List<ProductBuyedDTO> products,
        String cardNumber,
        TypeDTO type
) {
}
