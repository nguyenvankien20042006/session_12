package com.example.bai5;

import com.example.bai5.controller.ProductController;
import com.example.bai5.model.Product;
import com.example.bai5.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_HTTP200() throws Exception {

        List<Product> products = List.of(
                new Product("Laptop", 1000.0, 5)
        );

        when(productService.getAll()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    void getById_404() throws Exception {

        when(productService.getById(1L))
                .thenThrow(new RuntimeException("Product not found"));

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void post_201() throws Exception {

        Product product = new Product("Phone", 500.0, 2);

        when(productService.addProduct(org.mockito.ArgumentMatchers.any(Product.class)))
                .thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void delete_204() throws Exception {
        doNothing().when(productService).deleteProduct(1L);
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}
