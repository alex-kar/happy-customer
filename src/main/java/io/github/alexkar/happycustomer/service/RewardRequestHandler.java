package io.github.alexkar.happycustomer.service;

import io.github.alexkar.happycustomer.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardRequestHandler {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RewardPointsCalculator pointsCalculator;

    public long perCustomer(String customerId) {
        Long purchase = orderRepository.findPurchaseByCustomerId(customerId);
        return pointsCalculator.calc(purchase);
    }

    public long total() {
        Long purchase = orderRepository.findTotalPurchase();
        return pointsCalculator.calc(purchase);
    }
}
