package com.fiuza.fiap.order.core.usecases;

import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.enums.Status;
import com.fiuza.fiap.order.core.exceptions.NotFoundException;
import com.fiuza.fiap.order.core.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderByIdUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    private GetOrderByIdUseCase getOrderByIdUseCase;

    private UUID orderId;
    private Order order;

    @BeforeEach
    void setup() {
        getOrderByIdUseCase = new GetOrderByIdUseCase(orderGateway);

        orderId = UUID.randomUUID();
        order = new Order(orderId, null, UUID.randomUUID(), Status.ABERTO, new ArrayList<>(), "pix");
    }

    @Test
    void should_return_order_when_found() {
        when(orderGateway.getById(orderId)).thenReturn(Optional.of(order));

        Order result = getOrderByIdUseCase.execute(orderId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(orderId);
    }

    @Test
    void should_throw_not_found_exception_when_order_not_found() {
        when(orderGateway.getById(orderId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            getOrderByIdUseCase.execute(orderId);
        });

        assertThat(exception.getMessage()).isEqualTo("Pedido não encontrado");
    }
}
