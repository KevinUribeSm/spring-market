package com.curso.market.web.controller;

import com.curso.market.domain.Product;
import com.curso.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>>  getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("categoryId") int categoryId) {
        return new ResponseEntity<>(productService.getProductByCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }

    @GetMapping("/stock/{stock}")
    public Optional<List<Product>> getStockLess(@PathVariable("stock") int stock) {
        return productService.getStock(stock);
    }
}
