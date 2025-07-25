package com.fiuza.fiap.order.infra.feign.stock;

import com.fiuza.fiap.order.core.entities.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "stock-client", url = "${client.stock.url}")
public interface FeignStockClient {
    @GetMapping("/stock/{sku}")
    Stock findBySku(@PathVariable("sku") Long sku);

    @PutMapping("/stock/remove/{sku}/{quantity}")
    Stock update(@PathVariable("sku") Long sku, @PathVariable("quantity") Integer quantity);
}
