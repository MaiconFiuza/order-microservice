package com.fiuza.fiap.order.core.gateway;

import com.fiuza.fiap.order.core.entities.Stock;

public interface StockGateway {
    Stock get(Long sku);

    Stock updateStock(Long sku, Integer quantity);
}
