package com.fiuza.fiap.order.infra.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.fiap.order.core.dto.OrderEventDTO;
import com.fiuza.fiap.order.core.usecases.HandleOrderCreatedEventUseCase;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventListener {
    private final HandleOrderCreatedEventUseCase handleOrderCreatedEventUseCase;

    public OrderCreatedEventListener(HandleOrderCreatedEventUseCase handleOrderCreatedEventUseCase) {
        this.handleOrderCreatedEventUseCase = handleOrderCreatedEventUseCase;
    }

    @Transactional
    @KafkaListener(topics = "order-created", groupId = "order-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String event) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        OrderEventDTO orderEvent = mapper.readValue(event, OrderEventDTO.class);
        handleOrderCreatedEventUseCase.execute(orderEvent);
    }
}
