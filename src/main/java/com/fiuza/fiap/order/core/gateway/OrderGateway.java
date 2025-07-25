package com.fiuza.fiap.order.core.gateway;

import com.fiuza.fiap.order.core.entities.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderGateway {
    Order create(Order order);

    Optional<Order> getById(UUID id);

    Order update(Order order);
}
