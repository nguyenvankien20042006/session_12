package com.example.bai5;

import com.example.bai5.model.Product;
import com.example.bai5.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void getAll_HasData() {
        productService.addProduct(new Product("Laptop", 1000.0, 5));

        assertFalse(productService.getAll().isEmpty());
    }

    @Test
    void getById_Found() {
        Product product = productService.addProduct(
                new Product("Phone", 500.0, 2)
        );

        Product found = productService.getById(product.getId());

        assertEquals(product.getId(), found.getId());
    }

    @Test
    void getById_NotFound() {
        assertThrows(RuntimeException.class,
                () -> productService.getById(999L));
    }

    @Test
    void addProduct_Success() {
        Product product = productService.addProduct(
                new Product("Mouse", 50.0, 10)
        );

        assertNotNull(product.getId());
    }

    @Test
    void deleteProduct_Success() {
        Product product = productService.addProduct(
                new Product("Keyboard", 80.0, 3)
        );

        int before = productService.getAll().size();

        productService.deleteProduct(product.getId());

        int after = productService.getAll().size();

        assertEquals(before - 1, after);
    }
}
