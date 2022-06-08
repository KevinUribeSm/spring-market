package com.curso.market.domain.repository;

import com.curso.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findByCategory(int categoryId);
    Optional<List<Product>> getStock(int stock);
    Product getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
