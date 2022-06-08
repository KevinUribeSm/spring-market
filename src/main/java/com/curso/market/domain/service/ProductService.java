package com.curso.market.domain.service;

import com.curso.market.domain.Product;
import com.curso.market.domain.repository.ProductRepository;
import com.curso.market.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public List<Product> getProductByCategory(int categoryId) {
        return productRepository.findByCategory(categoryId);
    }

    public Optional<List<Product>> getStock(int stock) {
        return productRepository.getStock(stock);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        try {
            productRepository.delete(productId);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }

}
