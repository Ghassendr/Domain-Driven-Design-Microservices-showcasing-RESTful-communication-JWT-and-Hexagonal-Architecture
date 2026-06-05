package com.example.product.application.usecase;

import com.example.product.domain.model.Price;
import com.example.product.domain.model.Product;
import com.example.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    @Transactional
    public Product createProduct(String title, String description, Double priceAmount, List<String> images) {
        Product product = new Product(
                title,
                description,
                new Price(priceAmount),
                images);
        return productRepository.save(product);
    }
}
