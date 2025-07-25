package com.fiuza.fiap.order.infra.feign.payment;


import com.fiuza.fiap.order.core.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "payment-client", url = "${client.payment.url}")
public interface FeignPaymentClient {

    @PostMapping("/payment")
    UUID create(@RequestBody PaymentDTO paymentDTO);
}
