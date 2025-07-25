package com.fiuza.fiap.order.core.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockTest {

    @Test
    void testConstructorWithAllFields() {
        UUID id = UUID.randomUUID();
        Long sku = 12345L;
        Integer quantity = 20;

        Stock stock = new Stock(id, sku, quantity);

        assertThat(stock.getId()).isEqualTo(id);
        assertThat(stock.getProductSku()).isEqualTo(sku);
        assertThat(stock.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void testConstructorWithoutId() {
        Long sku = 67890L;
        Integer quantity = 15;

        Stock stock = new Stock(sku, quantity);

        assertThat(stock.getId()).isNull();
        assertThat(stock.getProductSku()).isEqualTo(sku);
        assertThat(stock.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void testSettersAndGetters() {
        Stock stock = new Stock();

        UUID id = UUID.randomUUID();
        Long sku = 11111L;
        Integer quantity = 5;

        stock.setId(id);
        stock.setProductSku(sku);
        stock.setQuantity(quantity);

        assertThat(stock.getId()).isEqualTo(id);
        assertThat(stock.getProductSku()).isEqualTo(sku);
        assertThat(stock.getQuantity()).isEqualTo(quantity);
    }
}
