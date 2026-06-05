package com.example.product.application.usecase;

import com.example.product.domain.model.Product;
import com.example.product.domain.model.ProductId;
import com.example.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublishProductUseCase {

    private final ProductRepository productRepository;

    @Transactional
    public void publishProduct(UUID productIdStr) {
        ProductId productId = new ProductId(productIdStr);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.publish();
        productRepository.save(product);
    }
}
