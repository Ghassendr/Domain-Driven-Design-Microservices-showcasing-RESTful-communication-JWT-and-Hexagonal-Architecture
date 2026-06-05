package com.example.product.interface_layer.controller;

import com.example.product.application.usecase.GetPublishedProductsUseCase;
import com.example.product.interface_layer.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final GetPublishedProductsUseCase getPublishedProductsUseCase;

    @GetMapping("/published")
    public List<ProductResponseDTO> getPublishedProducts() {
        return getPublishedProductsUseCase.getPublishedProducts().stream()
                .map(product -> ProductResponseDTO.builder()
                        .id(product.getId().getValue().toString())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice().getAmount())
                        .images(product.getImages())
                        .build())
                .collect(Collectors.toList());
    }
}
