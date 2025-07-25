package com.fiuza.fiap.order.infra.feign.customer;

import com.fiuza.fiap.order.core.entities.Customer;
import com.fiuza.fiap.order.core.gateway.CustomerGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerClientAdapter implements CustomerGateway {

    private final FeignCustomerClient feignCustomerClient;

    public CustomerClientAdapter(FeignCustomerClient feignCustomerClient) {
        this.feignCustomerClient = feignCustomerClient;
    }

    @Override
    public Customer get(UUID id) {
        return feignCustomerClient.findByCustomerId(id);
    }
}
