package com.example.client.domain.repository;

import com.example.client.domain.model.ProductView;
import java.util.List;

public interface ProductQueryRepository {
    List<ProductView> getPublishedProducts();
}
