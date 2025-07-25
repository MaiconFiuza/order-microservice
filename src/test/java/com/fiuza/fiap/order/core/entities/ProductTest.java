package com.fiuza.fiap.order.core.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductTest {

    @Test
    void testConstructorAndGetters() {
        Long sku = 123L;
        String name = "Produto Teste";
        BigDecimal price = new BigDecimal("99.99");

        Product product = new Product(sku, name, price);

        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualByComparingTo(price);
    }

    @Test
    void testSetters() {
        Product product = new Product();

        Long sku = 456L;
        String name = "Outro Produto";
        BigDecimal price = new BigDecimal("49.90");

        product.setSku(sku);
        product.setName(name);
        product.setPrice(price);

        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualByComparingTo(price);
    }
}
