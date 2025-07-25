package com.fiuza.fiap.order.infra.adapter;

import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.gateway.OrderGateway;
import com.fiuza.fiap.order.infra.mappers.OrderMapper;
import com.fiuza.fiap.order.infra.models.OrderModel;
import com.fiuza.fiap.order.infra.repository.OrderRepository;

import java.util.Optional;
import java.util.UUID;

public class OrderRepositoryImp implements OrderGateway {

    private final OrderRepository orderRepository;

    public OrderRepositoryImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order create(Order order) {
        OrderModel orderModel = OrderMapper.orderToOrderModel(order);
        OrderModel orderSaved = orderRepository.save(orderModel);
        return OrderMapper.orderModelToOrder(orderSaved);
    }

    @Override
    public Optional<Order> getById(UUID id) {
        return orderRepository.findById(id).map(OrderMapper::orderModelToOrder);
    }


    @Override
    public Order update(Order order) {
        OrderModel orderModel = OrderMapper.orderToOrderModel(order);
        OrderModel orderSaved = orderRepository.save(orderModel);
        return OrderMapper.orderModelToOrder(orderSaved);
    }
}
