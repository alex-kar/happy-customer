package io.github.alexkar.happycustomer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT SUM(o.price) FROM OrderEntity o WHERE o.customerId = ?1")
    Long findPurchaseByCustomerId(String customerId);

    @Query("SELECT SUM(o.price) FROM OrderEntity o GROUP BY o.customerId")
    Collection<Long> findAllPurchases();

}
