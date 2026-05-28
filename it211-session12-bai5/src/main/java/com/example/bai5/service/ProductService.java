package com.example.bai5.service;

import com.example.bai5.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public List<Product> getAll() {
        return products;
    }

    public Product getById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product addProduct(Product product) {
        Product newProduct = new Product(
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );

        products.add(newProduct);
        return newProduct;
    }

    public void deleteProduct(Long id) {
        Product product = getById(id);
        products.remove(product);
    }


    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getById(id);
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        return product;
    }
}
