package com.techplus.ecommerce.application.port.out;

import com.techplus.ecommerce.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findByName(String name);
    boolean isAvailable(String name);
    void save(Product product);
}
