package com.fiuza.fiap.order.infra.feign.customer;

import com.fiuza.fiap.order.core.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customer-client", url = "${client.customer.url}")
public interface FeignCustomerClient {

    @GetMapping("/user/{customerId}")
    Customer findByCustomerId(@PathVariable("customerId") UUID customerId);
}
