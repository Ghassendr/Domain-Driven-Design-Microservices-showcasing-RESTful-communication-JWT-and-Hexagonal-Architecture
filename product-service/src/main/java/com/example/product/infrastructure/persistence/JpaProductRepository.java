package com.example.product.infrastructure.persistence;

import com.example.product.domain.model.Product;
import com.example.product.domain.model.ProductId;
import com.example.product.domain.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, ProductId> {
    List<Product> findAllByStatus(ProductStatus status);
}
