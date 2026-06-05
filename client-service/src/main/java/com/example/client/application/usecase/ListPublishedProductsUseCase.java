package com.example.client.application.usecase;

import com.example.client.domain.model.ProductView;
import com.example.client.domain.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPublishedProductsUseCase {

    private final ProductQueryRepository productQueryRepository;

    public List<ProductView> execute() {
        return productQueryRepository.getPublishedProducts();
    }
}
