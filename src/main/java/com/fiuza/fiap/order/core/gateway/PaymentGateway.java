package com.fiuza.fiap.order.core.gateway;


import com.fiuza.fiap.order.core.enums.Status;

import java.util.UUID;

public interface PaymentGateway {
    UUID create(UUID orderId, UUID customerId, Status status);
}
