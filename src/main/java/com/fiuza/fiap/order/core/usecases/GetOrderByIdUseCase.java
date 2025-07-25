package com.fiuza.fiap.order.core.usecases;

import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.exceptions.InternalServerError;
import com.fiuza.fiap.order.core.exceptions.NotFoundException;
import com.fiuza.fiap.order.core.gateway.OrderGateway;

import java.util.UUID;

public class GetOrderByIdUseCase {
    private final OrderGateway orderGateway;

    public GetOrderByIdUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order execute(UUID id) {
        try {
            return orderGateway.getById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError("Aconteceu um erro inesperado. por favor tente novamente");
        }
    }


}
