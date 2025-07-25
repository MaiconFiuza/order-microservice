package com.fiuza.fiap.order.application.controller;


import com.fiuza.fiap.order.application.controllers.OrderController;
import com.fiuza.fiap.order.core.dto.ProductBuyedDTO;
import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.entities.ProductBuyed;
import com.fiuza.fiap.order.core.enums.Status;
import com.fiuza.fiap.order.core.exceptions.NotFoundException;
import com.fiuza.fiap.order.core.usecases.GetOrderByIdUseCase;
import com.fiuza.fiap.order.helper.ProductBuyedHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetOrderByIdUseCase getOrderByIdUseCase;

    private UUID orderId;
    private Order order;

    @BeforeEach
    void setup() {
        orderId = UUID.randomUUID();
        order = new Order(
                orderId,
                UUID.randomUUID(),
                UUID.randomUUID(),
                Status.ABERTO,
                List.of(
                        ProductBuyedHelper.createProductBuyedDefault()
                ),
                "pix"
        );
    }

    @Test
    void shouldReturnOrderById() throws Exception {
        Mockito.when(getOrderByIdUseCase.execute(orderId)).thenReturn(order);

        mockMvc.perform(get("/order/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId.toString()))
                .andExpect(jsonPath("$.status").value("ABERTO"));
    }

    @Test
    void testGetOrderByIdNotFound() throws Exception {
        UUID orderId = UUID.randomUUID();

        Mockito.when(getOrderByIdUseCase.execute(orderId)).thenThrow(new NotFoundException("Pedido não encontrado"));

        mockMvc.perform(get("/order/{id}", orderId))
                .andExpect(status().isNotFound());
    }
}
