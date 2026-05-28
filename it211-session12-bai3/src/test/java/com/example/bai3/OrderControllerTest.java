package com.example.bai3;

import com.example.bai3.controller.OrderController;
import com.example.bai3.model.Order;
import com.example.bai3.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    void getAllOrderSuccess() throws Exception {
        Order order = new Order(
                "John Doe",
                "Product 1",
                2,
                100.0
        );
        when(orderService.getAllOrders())
                .thenReturn(List.of(order));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void createOrderSuccess() throws Exception {

        Order requestOrder = new Order(
                "John Doe",
                "Product 1",
                2,
                100.0
        );

        when(orderService.addOrder(requestOrder))
                .thenReturn(requestOrder);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestOrder)))
                .andExpect(status().isCreated());
    }

    @Test
    void getOrderByIdSuccess() throws Exception {
        Order order = new Order(
                "John Doe",
                "Product 1",
                2,
                100.0
        );

        when(orderService.findById(1L))
                .thenReturn(order);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value(order.getCustomerName()));
    }

    @Test
    void getOrderByIdNotFound() throws Exception {

        when(orderService.findById(2L))
                .thenThrow(new RuntimeException("Order not found"));

        mockMvc.perform(get("/api/orders/2"))
                .andExpect(status().isNotFound());
    }
}
