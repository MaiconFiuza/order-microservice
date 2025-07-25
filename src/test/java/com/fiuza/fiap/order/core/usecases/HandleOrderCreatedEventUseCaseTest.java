package com.fiuza.fiap.order.core.usecases;

import com.fiuza.fiap.order.core.dto.OrderEventDTO;
import com.fiuza.fiap.order.core.dto.ProductBuyedDTO;
import com.fiuza.fiap.order.core.dto.TypeDTO;
import com.fiuza.fiap.order.core.entities.Customer;
import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.entities.ProductBuyed;
import com.fiuza.fiap.order.core.entities.Stock;
import com.fiuza.fiap.order.core.enums.Status;
import com.fiuza.fiap.order.core.exceptions.BadRequest;
import com.fiuza.fiap.order.core.exceptions.InternalServerError;
import com.fiuza.fiap.order.core.exceptions.NotFoundException;
import com.fiuza.fiap.order.core.gateway.CustomerGateway;
import com.fiuza.fiap.order.core.gateway.OrderGateway;
import com.fiuza.fiap.order.core.gateway.PaymentGateway;
import com.fiuza.fiap.order.core.gateway.StockGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HandleOrderCreatedEventUseCaseTest {
    @Mock
    PaymentGateway paymentGateway;

    @Mock
    StockGateway stockGateway;

    @Mock
    CustomerGateway customerGateway;

    @Mock
    OrderGateway orderGateway;

    @InjectMocks
    HandleOrderCreatedEventUseCase handleOrderCreatedEventUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        handleOrderCreatedEventUseCase = new HandleOrderCreatedEventUseCase(
                paymentGateway,
                stockGateway,
                customerGateway,
                orderGateway
        );
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    UUID orderId = UUID.randomUUID();
    UUID customerId = UUID.randomUUID();
    UUID paymentId = UUID.randomUUID();



    Customer customer = new Customer(
            customerId,
            "12345678900",
            "Fulano da Silva",
            LocalDate.of(1990, 1, 1),
            "fulano",
            "senha123",
            "Rua Exemplo, 123"
    );


    @Test
    void should_process_order_successfully() {
        UUID productId = UUID.randomUUID();
        var productBuyedDTO = new ProductBuyedDTO(1L, 2);
        var orderEventDTO = new OrderEventDTO(orderId, customerId, List.of(productBuyedDTO), "1234567890123456", TypeDTO.FECHADO_COM_SUCESSO);

        var order = new Order(orderId, null, customerId, Status.ABERTO, new ArrayList<>(), "1234567890123456");
        var productBuyed = new ProductBuyed(productId, productBuyedDTO.sku(), productBuyedDTO.quantity(), order);
        order.getProducts().add(productBuyed);

        when(customerGateway.get(customerId)).thenReturn(customer);
        when(orderGateway.getById(orderId)).thenReturn(Optional.of(order));
        when(stockGateway.get(productBuyedDTO.sku()))
                .thenReturn(new Stock(UUID.randomUUID(), productBuyedDTO.sku(), 10));
        when(stockGateway.updateStock(productBuyedDTO.sku(), productBuyedDTO.quantity()))
                .thenReturn(new Stock(productBuyedDTO.sku(), 8));
        when(paymentGateway.create(orderId, customerId, Status.FECHADO_COM_SUCESSO)).thenReturn(paymentId);
        when(orderGateway.update(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        Order updatedOrder = handleOrderCreatedEventUseCase.execute(orderEventDTO);

        assertThat(updatedOrder).isNotNull();
        assertThat(updatedOrder.getPaymentId()).isEqualTo(paymentId);
        assertThat(updatedOrder.getStatus()).isEqualTo(Status.FECHADO_COM_SUCESSO);

        verify(stockGateway).updateStock(productBuyedDTO.sku(), productBuyedDTO.quantity());
        verify(orderGateway).update(any(Order.class));
    }

    @Test
    void should_throw_not_found_if_order_does_not_exist() {
        var dto = new OrderEventDTO(orderId, customerId, List.of(), null, TypeDTO.FECHADO_COM_SUCESSO);
        when(orderGateway.getById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> handleOrderCreatedEventUseCase.execute(dto));
    }


    @Test
    void should_throw_internal_server_error_on_unexpected_exception() {
        var dto = new OrderEventDTO(orderId, customerId, List.of(), null, TypeDTO.FECHADO_COM_SUCESSO);

        when(customerGateway.get(customerId)).thenThrow(new RuntimeException("Falha inesperada"));

        assertThrows(InternalServerError.class, () -> handleOrderCreatedEventUseCase.execute(dto));
    }


}
