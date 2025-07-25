package com.fiuza.fiap.order.infra.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.fiap.order.core.dto.OrderEventDTO;
import com.fiuza.fiap.order.core.dto.ProductBuyedDTO;
import com.fiuza.fiap.order.core.dto.TypeDTO;
import com.fiuza.fiap.order.core.usecases.HandleOrderCreatedEventUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class OrderCreatedEventListenerTest {
    private HandleOrderCreatedEventUseCase handleOrderCreatedEventUseCase;
    private OrderCreatedEventListener listener;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        handleOrderCreatedEventUseCase = mock(HandleOrderCreatedEventUseCase.class);
        listener = new OrderCreatedEventListener(handleOrderCreatedEventUseCase);
    }

    @Test
    void listen_shouldDeserializeJsonAndCallUseCase() throws JsonProcessingException {
        UUID orderId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        List<ProductBuyedDTO> products = List.of(
                new ProductBuyedDTO(123L, 2)
        );

        OrderEventDTO dto = new OrderEventDTO(orderId, customerId, products, "1234567890123456", TypeDTO.FECHADO_COM_SUCESSO);
        String json = objectMapper.writeValueAsString(dto);

        listener.listen(json);

        ArgumentCaptor<OrderEventDTO> captor = ArgumentCaptor.forClass(OrderEventDTO.class);
        verify(handleOrderCreatedEventUseCase, times(1)).execute(captor.capture());

        OrderEventDTO captured = captor.getValue();
        assertThat(captured.orderId()).isEqualTo(orderId);
        assertThat(captured.customerId()).isEqualTo(customerId);
        assertThat(captured.cardNumber()).isEqualTo("1234567890123456");
        assertThat(captured.type()).isEqualTo(TypeDTO.FECHADO_COM_SUCESSO);
    }
}
