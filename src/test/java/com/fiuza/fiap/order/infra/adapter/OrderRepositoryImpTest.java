package com.fiuza.fiap.order.infra.adapter;

import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.helper.OrderHelper;
import com.fiuza.fiap.order.helper.OrderModelHelper;
import com.fiuza.fiap.order.infra.models.OrderModel;
import com.fiuza.fiap.order.infra.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryImpTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderRepositoryImp orderRepositoryImp;

    private AutoCloseable mocks;

    private UUID orderId;
    private Order order;
    private OrderModel orderModel;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
        orderRepositoryImp = new OrderRepositoryImp(orderRepository);

        orderId = UUID.randomUUID();
        order = OrderHelper.createOrderWithId(orderId);

        orderModel = OrderModelHelper.createOrderModelWithId(
                order.getId(),
                order.getCustomerId(),
                order.getPaymentId(),
                order.getStatus(),
                order.getProducts(),
                order.getPaymentType()
        );
    }

    @AfterEach
    void teardown() throws Exception {
        mocks.close();
    }

    @Test
    void create_success() {
        when(orderRepository.save(any(OrderModel.class))).thenReturn(orderModel);

        Order savedOrder = orderRepositoryImp.create(order);

        ArgumentCaptor<OrderModel> captor = ArgumentCaptor.forClass(OrderModel.class);
        verify(orderRepository, times(1)).save(captor.capture());

        OrderModel capturedOrderModel = captor.getValue();

        assertThat(capturedOrderModel.getId()).isEqualTo(order.getId());
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isEqualTo(order.getId());
    }

    @Test
    void getById_found() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderModel));

        Optional<Order> result = orderRepositoryImp.getById(orderId);

        verify(orderRepository, times(1)).findById(orderId);
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(orderId);
    }

    @Test
    void getById_notFound() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        Optional<Order> result = orderRepositoryImp.getById(orderId);

        verify(orderRepository, times(1)).findById(orderId);
        assertThat(result).isEmpty();
    }

    @Test
    void update_success() {
        when(orderRepository.save(any(OrderModel.class))).thenReturn(orderModel);

        Order updatedOrder = orderRepositoryImp.update(order);

        ArgumentCaptor<OrderModel> captor = ArgumentCaptor.forClass(OrderModel.class);
        verify(orderRepository, times(1)).save(captor.capture());

        OrderModel capturedOrderModel = captor.getValue();

        assertThat(capturedOrderModel.getId()).isEqualTo(order.getId());
        assertThat(updatedOrder).isNotNull();
        assertThat(updatedOrder.getId()).isEqualTo(order.getId());
    }
}
