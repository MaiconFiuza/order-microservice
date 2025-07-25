package com.fiuza.fiap.order.core.gateway;

import com.fiuza.fiap.order.core.entities.Product;

public interface ProductGateway {
    Product getProductBySku(Long sku);
}
