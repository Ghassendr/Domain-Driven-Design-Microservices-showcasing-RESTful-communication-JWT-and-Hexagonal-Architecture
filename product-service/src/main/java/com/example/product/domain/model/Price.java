package com.example.product.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price {
    private Double amount;
    private String currency;

    public Price(Double amount) {
        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("Price amount cannot be negative or null");
        }
        this.amount = amount;
        this.currency = "EUR"; // Defaulting strictly for now as per simple requirements
    }
}
