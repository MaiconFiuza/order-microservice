package com.fiuza.fiap.order.infra.feign;

import com.fiuza.fiap.order.core.entities.Stock;
import com.fiuza.fiap.order.infra.feign.stock.FeignStockClient;
import com.fiuza.fiap.order.infra.feign.stock.StockClientAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class StockClientAdapterTest {

    @Mock
    private FeignStockClient feignStockClient;

    private StockClientAdapter stockClientAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stockClientAdapter = new StockClientAdapter(feignStockClient);
    }

    @Test
    void get_shouldReturnStockFromFeignClient() {
        Long sku = 123L;
        Stock expectedStock = new Stock(sku, 10);
        when(feignStockClient.findBySku(sku)).thenReturn(expectedStock);

        Stock actualStock = stockClientAdapter.get(sku);

        assertThat(actualStock).isEqualTo(expectedStock);
        verify(feignStockClient, times(1)).findBySku(sku);
    }

    @Test
    void updateStock_shouldReturnUpdatedStockFromFeignClient() {
        Long sku = 123L;
        Integer quantity = 5;
        Stock expectedStock = new Stock(sku, quantity);
        when(feignStockClient.update(sku, quantity)).thenReturn(expectedStock);

        Stock actualStock = stockClientAdapter.updateStock(sku, quantity);

        assertThat(actualStock).isEqualTo(expectedStock);
        verify(feignStockClient, times(1)).update(sku, quantity);
    }
}
