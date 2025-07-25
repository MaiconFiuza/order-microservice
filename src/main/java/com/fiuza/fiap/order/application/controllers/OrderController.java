package com.fiuza.fiap.order.application.controllers;

import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.usecases.GetOrderByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static  final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final GetOrderByIdUseCase getOrderByIdUseCase;

    public OrderController(GetOrderByIdUseCase getOrderByIdUseCase) {
        this.getOrderByIdUseCase = getOrderByIdUseCase;
    }

    @Operation(
            description = "Busca um pedido",
            summary = "Endpoint responsável por busca pedido",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable UUID id) {
        logger.info("GET /{}", id);
        var order = getOrderByIdUseCase.execute(id);
        return ResponseEntity.ok(order);
    }

}
