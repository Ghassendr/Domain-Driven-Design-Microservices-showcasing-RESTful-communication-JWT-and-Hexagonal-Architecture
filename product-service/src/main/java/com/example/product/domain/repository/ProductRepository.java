package com.example.product.domain.repository;

import com.example.product.domain.model.Product;
import com.example.product.domain.model.ProductId;
import com.example.product.domain.model.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(ProductId id);

    List<Product> findAllByStatus(ProductStatus status);
}
