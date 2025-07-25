package com.fiuza.fiap.order.core.gateway;

import com.fiuza.fiap.order.core.entities.Customer;

import java.util.UUID;

public interface CustomerGateway {
    Customer get(UUID id);
}
