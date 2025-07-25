package com.fiuza.fiap.order.helper;

import com.fiuza.fiap.order.infra.models.OrderModel;
import com.fiuza.fiap.order.infra.models.ProductBuyedModel;

import java.util.UUID;

public class ProductBuyedModelHelper {
    public static ProductBuyedModel createProductBuyedModelWithOrder(OrderModel orderModel) {
        ProductBuyedModel productModel = new ProductBuyedModel();
        productModel.setId(UUID.randomUUID());
        productModel.setSku(1L);
        productModel.setQuantity(5);
        productModel.setOrder(orderModel);
        return productModel;
    }
}
