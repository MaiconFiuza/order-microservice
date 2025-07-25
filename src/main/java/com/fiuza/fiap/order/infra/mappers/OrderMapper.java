package com.fiuza.fiap.order.infra.mappers;

import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.entities.ProductBuyed;
import com.fiuza.fiap.order.core.enums.Status;
import com.fiuza.fiap.order.infra.models.OrderModel;
import com.fiuza.fiap.order.infra.models.ProductBuyedModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderModel orderToOrderModel(Order order) {
        return new OrderModel(
                order.getId(), order.getCustomerId(), order.getPaymentId(),
                Status.valueOf(order.getStatus().name()),
                productBuyedToProductBuyedModel(order),
                order.getPaymentType()
        );
    }

    public static Order orderModelToOrder(OrderModel orderModel) {
        return new Order(
                orderModel.getId(), orderModel.getPaymentId(),orderModel.getCustomerId(),
                Status.valueOf(orderModel.getStatus().name()), productBuyedModelToProductBuyed(orderModel),
                orderModel.getPaymentType()

        );
    }

    public static List<ProductBuyedModel> productBuyedToProductBuyedModel(Order order) {
        OrderModel parentOrder = new OrderModel(
                order.getId(),
                order.getCustomerId(),
                order.getPaymentId(),
                Status.valueOf(order.getStatus().name()),
                new ArrayList<>(),
                order.getPaymentType()
        );

        return order.getProducts()
                .stream()
                .map(it -> new ProductBuyedModel(
                        it.getId(),
                        it.getSku(),
                        it.getQuantity(),
                        parentOrder
                )).collect(Collectors.toCollection(ArrayList::new));
    }


    private static List<ProductBuyed> productBuyedModelToProductBuyed(OrderModel orderModel) {
        // Cria a instância única do Order
        Order parentOrder = new Order(
                orderModel.getId(),
                orderModel.getCustomerId(),
                orderModel.getPaymentId(),
                orderModel.getStatus(),
                new ArrayList<>(),
                orderModel.getPaymentType()
        );


        return orderModel.getProducts()
                .stream()
                .map(it -> new ProductBuyed(
                        it.getId(),
                        it.getSku(),
                        it.getQuantity(),
                        parentOrder
                ))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
