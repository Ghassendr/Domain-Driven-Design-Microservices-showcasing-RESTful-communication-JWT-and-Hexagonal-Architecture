package com.example.product.infrastructure.persistence;

import com.example.product.domain.model.Product;
import com.example.product.domain.model.ProductId;
import com.example.product.domain.model.ProductStatus;
import com.example.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product save(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return jpaProductRepository.findById(id);
    }

    @Override
    public List<Product> findAllByStatus(ProductStatus status) {
        return jpaProductRepository.findAllByStatus(status);
    }
}
