package com.fiuza.fiap.order.helper;

import com.fiuza.fiap.order.core.entities.ProductBuyed;
import com.fiuza.fiap.order.core.enums.Status;
import com.fiuza.fiap.order.infra.models.OrderModel;
import com.fiuza.fiap.order.infra.models.ProductBuyedModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderModelHelper {
    public static OrderModel createOrderModelWithId(UUID id, UUID customerId, UUID paymentId, Status status, List<ProductBuyed> products, String paymentType) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(id);
        orderModel.setCustomerId(customerId);
        orderModel.setPaymentId(paymentId);
        orderModel.setStatus(status);
        orderModel.setPaymentType(paymentType);

        List<ProductBuyedModel> productModels = toProductBuyedModelList(products, orderModel);
        orderModel.setProducts(productModels);

        return orderModel;
    }

    public static List<ProductBuyedModel> toProductBuyedModelList(List<ProductBuyed> products, OrderModel orderModel) {
        if (products == null) return new ArrayList<>();

        return products.stream().map(p -> {
            ProductBuyedModel pm = new ProductBuyedModel();
            pm.setId(p.getId() != null ? p.getId() : UUID.randomUUID());
            pm.setSku(p.getSku());
            pm.setQuantity(p.getQuantity());
            pm.setOrder(orderModel);
            return pm;
        }).collect(Collectors.toList());
    }

    public static OrderModel createOrderModelWithId() {
        UUID id = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        UUID paymentId = UUID.randomUUID();
        Status status = Status.ABERTO;
        String paymentType = "pix";

        List<ProductBuyed> products = new ArrayList<>();
        products.add(new ProductBuyed(UUID.randomUUID(), 1L, 5, null));

        return createOrderModelWithId(id, customerId, paymentId, status, products, paymentType);
    }
}
