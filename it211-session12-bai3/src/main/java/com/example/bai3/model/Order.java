package com.example.bai3.model;

import lombok.Data;

@Data
public class Order {
    private static Long index = 0L;
    private Long id;
    private String customerName;
    private String product;
    private Integer quantity;
    private Double totalAmount;

    public Order(String customerName, String product, Integer quantity, Double totalAmount) {
        index += 1;
        this.id = index;
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }


}
