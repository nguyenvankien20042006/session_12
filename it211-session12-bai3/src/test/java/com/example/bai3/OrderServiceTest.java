package com.example.bai3;

import com.example.bai3.model.Order;
import com.example.bai3.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Test
    void getAllOrders_ReturnNonEmptyList() {
        List<Order> orders = orderService.getAllOrders();
        assertEquals(0, orders.size());
    }

    @Test
    void getOrderById_Found() {
        Order order = new Order("John Doe", "Product 1", 2, 100.0);
        orderService.addOrder(order);
        Order foundOrder = orderService.findById(order.getId());
        assertEquals(order, foundOrder);
    }

    @Test
    void getOrderById_NotFound_ThrowException() {
        assertThrows(RuntimeException.class, () -> orderService.findById(1L));
    }

    @Test
    void addOrder_Success() {
        Order order = new Order("John Doe", "Product 1", 2, 100.0);
        Order addedOrder = orderService.addOrder(order);
        assertEquals(order, addedOrder);
    }

    @Test
    void updateOrder_Success() {
        Order order = new Order("John Doe", "Product 1", 2, 100.0);
        orderService.addOrder(order);
        order.setCustomerName("Jane Doe");
        Order updatedOrder = orderService.updateOrder(order.getId(), order);
        assertEquals("Jane Doe", updatedOrder.getCustomerName());
    }

    @Test
    void deleteOrder_RemovesElement() {
        Order order1 = new Order("John Doe", "Product 1", 2, 100.0);
        Order order2 = new Order("John Doe", "Product 1", 2, 100.0);
        orderService.addOrder(order1);
        orderService.addOrder(order2);
        orderService.removeOrder(order1.getId());
        assertEquals(1, orderService.getAllOrders().size());
    }
}
