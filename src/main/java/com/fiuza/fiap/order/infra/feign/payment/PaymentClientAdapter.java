package com.fiuza.fiap.order.infra.feign.payment;

import com.fiuza.fiap.order.core.dto.PaymentDTO;
import com.fiuza.fiap.order.core.enums.Status;
import com.fiuza.fiap.order.core.gateway.PaymentGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentClientAdapter implements PaymentGateway {

    private final FeignPaymentClient feignPaymentClient;

    public PaymentClientAdapter(FeignPaymentClient feignPaymentClient) {
        this.feignPaymentClient = feignPaymentClient;
    }

    @Override
    public UUID create(UUID orderId, UUID customerId, Status status) {
        return feignPaymentClient.create(new PaymentDTO(orderId, customerId, status));
    }
}
