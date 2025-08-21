package com.techplus.ecommerce.adapter.out;

import com.techplus.ecommerce.application.port.out.ProductRepository;
import com.techplus.ecommerce.domain.model.Product;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ProductRepositoryInMemory implements ProductRepository {

    // Tabela PRODUCTS
    private final Map<String, Product> products = new ConcurrentHashMap<>();


    @Override
    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(products.get(name));
    }

    @Override
    public boolean isAvailable(String name) {
        return products.containsKey(name) && products.get(name).isAvailable();
    }

    @Override
    public void save(Product product) {
        products.put(product.getName(), product);
    }
}
