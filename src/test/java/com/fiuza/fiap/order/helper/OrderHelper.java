package com.fiuza.fiap.order.helper;

import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.entities.ProductBuyed;
import com.fiuza.fiap.order.core.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderHelper {
    public static Order createOrderDefault() {
        UUID customerId = UUID.randomUUID();
        List<ProductBuyed> products = new ArrayList<>();
        products.add(ProductBuyedHelper.createProductBuyedDefault());

        return new Order(
                UUID.randomUUID(),
                null,
                customerId,
                Status.ABERTO,
                products,
                "pix"
        );
    }

    public static Order createOrderWithId(UUID id) {
        UUID customerId = UUID.randomUUID();
        List<ProductBuyed> products = new ArrayList<>();
        products.add(ProductBuyedHelper.createProductBuyedDefault());

        return new Order(
                id,
                UUID.randomUUID(),
                customerId,
                Status.ABERTO,
                products,
                "pix"
        );
    }

    public static Order createOrderWithoutId(UUID customerId, Status status, String paymentType) {
        List<ProductBuyed> products = new ArrayList<>();
        products.add(ProductBuyedHelper.createProductBuyedDefault());

        return new Order(
                customerId,
                status,
                paymentType
        );
    }
}
