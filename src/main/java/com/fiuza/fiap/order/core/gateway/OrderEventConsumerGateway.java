package com.fiuza.fiap.order.core.gateway;

import com.fiuza.fiap.order.core.entities.Order;

public interface OrderEventConsumerGateway {
    void consumer(Order order);
}
