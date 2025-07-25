package com.fiuza.fiap.order.infra.feign.stock;

import com.fiuza.fiap.order.core.entities.Stock;
import com.fiuza.fiap.order.core.gateway.StockGateway;
import org.springframework.stereotype.Component;

@Component
public class StockClientAdapter implements StockGateway {

    private final FeignStockClient feignStockClient;

    public StockClientAdapter(FeignStockClient feignStockClient) {
        this.feignStockClient = feignStockClient;
    }

    @Override
    public Stock get(Long sku) {
        return feignStockClient.findBySku(sku);
    }

    @Override
    public Stock updateStock(Long sku, Integer quantity) {
        return feignStockClient.update(sku,quantity);
    }
}
