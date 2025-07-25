package com.fiuza.fiap.order.infra.config;

import com.fiuza.fiap.order.core.gateway.*;
import com.fiuza.fiap.order.core.usecases.GetOrderByIdUseCase;
import com.fiuza.fiap.order.core.usecases.HandleOrderCreatedEventUseCase;
import com.fiuza.fiap.order.infra.adapter.OrderRepositoryImp;
import com.fiuza.fiap.order.infra.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigs {
    @Bean
    public OrderGateway orderGateway(OrderRepository orderRepository) {
        return new OrderRepositoryImp(orderRepository);
    }

    @Bean
    public GetOrderByIdUseCase getOrderByIdUseCase(OrderGateway orderGateway) {
        return new GetOrderByIdUseCase(orderGateway);
    }

    @Bean
    public HandleOrderCreatedEventUseCase handleOrderCreatedEventUseCase(
     PaymentGateway paymentClient,
    StockGateway stockGateway,
    CustomerGateway customerGateway,
     OrderGateway orderGateway
    ) {
        return new HandleOrderCreatedEventUseCase(
                paymentClient,
                stockGateway,
                customerGateway,
                orderGateway
        );
    }
}
