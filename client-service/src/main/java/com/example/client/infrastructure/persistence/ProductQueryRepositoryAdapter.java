package com.example.client.infrastructure.persistence;

import com.example.client.domain.model.Price;
import com.example.client.domain.model.ProductView;
import com.example.client.domain.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductQueryRepositoryAdapter implements ProductQueryRepository {

        private final RestTemplate restTemplate;
        private static final String PRODUCT_SERVICE_URL = "http://localhost:8081/api/products/published";

        @SuppressWarnings("unchecked")
        @Override
        public List<ProductView> getPublishedProducts() {
                ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                                PRODUCT_SERVICE_URL,
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                                });

                List<Map<String, Object>> products = response.getBody();

                if (products == null) {
                        return List.of();
                }

                return products.stream()
                                .map(p -> ProductView.builder()
                                                .id((String) p.get("id"))
                                                .title((String) p.get("title"))
                                                .description((String) p.get("description"))
                                                .price(new Price(((Number) p.get("price")).doubleValue(), "EUR"))
                                                .images((List<String>) p.get("images"))
                                                .build())
                                .collect(Collectors.toList());
        }
}
