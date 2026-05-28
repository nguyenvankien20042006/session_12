package com.example.bai3.service;

import com.example.bai3.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private List<Order> orders;

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    public Order addOrder(Order order) {
        this.orders.add(order);
        return order;
    }

    public Order findById(Long id) {
        return this.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return this.orders;
    }

    public void removeOrder(Long id) {
        Order order = this.findById(id);
        this.orders.remove(order);
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = this.findById(id);
        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setProduct(order.getProduct());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setTotalAmount(order.getTotalAmount());
        return existingOrder;
    }

}
