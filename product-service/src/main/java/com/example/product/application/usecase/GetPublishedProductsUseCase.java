package com.example.product.application.usecase;

import com.example.product.domain.model.Product;
import com.example.product.domain.model.ProductStatus;
import com.example.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPublishedProductsUseCase {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getPublishedProducts() {
        return productRepository.findAllByStatus(ProductStatus.PUBLISHED);
    }
}
