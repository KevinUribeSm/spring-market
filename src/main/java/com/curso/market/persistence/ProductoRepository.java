package com.curso.market.persistence;

import com.curso.market.domain.Product;
import com.curso.market.domain.repository.ProductRepository;
import com.curso.market.persistence.crud.ProductoCrudRepository;
import com.curso.market.persistence.entity.Producto;
import com.curso.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> findAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    public List<Product> findByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getStock(int cantidadStock) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock, true);
        return productos.map(prod -> mapper.toProducts(prod));
    }

    @Override
    public Product getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto)).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

}
