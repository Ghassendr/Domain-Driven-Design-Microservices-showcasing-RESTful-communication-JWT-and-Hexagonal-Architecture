package com.example.product.domain.event;

import com.example.product.domain.model.ProductId;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ProductPublishedEvent implements DomainEvent {
    ProductId productId;
    LocalDateTime occurredOn;

    public ProductPublishedEvent(ProductId productId) {
        this.productId = productId;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
