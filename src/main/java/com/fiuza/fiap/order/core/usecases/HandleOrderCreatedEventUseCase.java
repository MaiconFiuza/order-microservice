package com.fiuza.fiap.order.core.usecases;

import com.fiuza.fiap.order.core.dto.OrderEventDTO;
import com.fiuza.fiap.order.core.entities.Order;
import com.fiuza.fiap.order.core.enums.Status;
import com.fiuza.fiap.order.core.exceptions.BadRequest;
import com.fiuza.fiap.order.core.exceptions.InternalServerError;
import com.fiuza.fiap.order.core.exceptions.NotFoundException;
import com.fiuza.fiap.order.core.gateway.*;


public class HandleOrderCreatedEventUseCase {

    private final PaymentGateway paymentClient;
    private final StockGateway stockGateway;
    private final CustomerGateway customerGateway;
    private final OrderGateway orderGateway;

    public HandleOrderCreatedEventUseCase(
       PaymentGateway paymentClient,
       StockGateway stockGateway,
       CustomerGateway customerGateway,
       OrderGateway orderGateway
    ) {
        this.paymentClient = paymentClient;
        this.stockGateway = stockGateway;
        this.customerGateway = customerGateway;
        this.orderGateway =orderGateway;
    }

    public Order execute(OrderEventDTO orderEventDTO) {
        try {
            var productsRequested = orderEventDTO.products();
            var user = customerGateway.get(orderEventDTO.customerId());

            var order = orderGateway.getById(orderEventDTO.orderId())
                    .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

            var statusOperation = Status.valueOf(orderEventDTO.type().name());

            if(statusOperation.name() != "FECHADO_COM_SUCESSO") {
                var failedResult = orderGateway
                        .update(new Order(order.getId(), null, user.getId(),
                                statusOperation, order.getProducts(), order.getPaymentType()));
                return failedResult;
            }


            for (var it : productsRequested) {
                var stock = stockGateway.get(it.sku());
                if (it == null || it.quantity() > stock.getQuantity()) {
                    var failedStock = orderGateway
                            .update(new Order(order.getId(), null, user.getId(),
                                    Status.FECHADO_SEM_ESTOQUE, order.getProducts(), order.getPaymentType()));
                    return failedStock;
                }
                stockGateway.updateStock(it.sku(), it.quantity());
            }


            var paymentId = paymentClient.create(order.getId(), user.getId(), statusOperation);

            var orderUpdated = new Order(order.getId(), paymentId, user.getId(), statusOperation, order.getProducts(),
                    order.getPaymentType());

            return orderGateway.update(orderUpdated);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (BadRequest e) {
            throw new BadRequest(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError("Erro interno, por favor tentar novamente");
        }

    }
}
