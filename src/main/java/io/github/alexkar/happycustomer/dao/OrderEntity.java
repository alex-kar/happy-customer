package io.github.alexkar.happycustomer.dao;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long price;
    private String customerId;
    private String itemId;


    public OrderEntity() {

    }

    public OrderEntity(Long price, String customerId, String itemId) {
        this.price = price;
        this.customerId = customerId;
        this.itemId = itemId;
    }
}
