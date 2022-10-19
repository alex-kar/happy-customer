package io.github.alexkar.happycustomer.service;

import io.github.alexkar.happycustomer.dao.OrderEntity;
import io.github.alexkar.happycustomer.dao.OrderRepository;
import io.github.alexkar.happycustomer.dto.SaleRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleRequestHandler {
    @Autowired
    private OrderRepository orderRepository;

    public void handleSaleRequest(SaleRequestV1 request) {
        OrderEntity order = new OrderEntity(request.getPrice(), request.getCustomerId(), request.getItemId());
        orderRepository.save(order);
    }

}
