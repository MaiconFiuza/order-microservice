package com.fiuza.fiap.order.core.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductBuyedTest {

    @Test
    void testConstructorAndGetters() {
        UUID id = UUID.randomUUID();
        Long sku = 12345L;
        Integer quantity = 10;
        Order order = new Order();

        ProductBuyed productBuyed = new ProductBuyed(id, sku, quantity, order);

        assertThat(productBuyed.getId()).isEqualTo(id);
        assertThat(productBuyed.getSku()).isEqualTo(sku);
        assertThat(productBuyed.getQuantity()).isEqualTo(quantity);
        assertThat(productBuyed.getOrder()).isEqualTo(order);
    }

    @Test
    void testSetters() {
        ProductBuyed productBuyed = new ProductBuyed(null, null, null, null);

        UUID id = UUID.randomUUID();
        Long sku = 54321L;
        Integer quantity = 5;
        Order order = new Order();

        productBuyed.setId(id);
        productBuyed.setSku(sku);
        productBuyed.setQuantity(quantity);
        productBuyed.setOrder(order);

        assertThat(productBuyed.getId()).isEqualTo(id);
        assertThat(productBuyed.getSku()).isEqualTo(sku);
        assertThat(productBuyed.getQuantity()).isEqualTo(quantity);
        assertThat(productBuyed.getOrder()).isEqualTo(order);
    }
}