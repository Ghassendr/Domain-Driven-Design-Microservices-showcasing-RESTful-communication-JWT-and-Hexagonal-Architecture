package com.example.product.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class Product {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private ProductId id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price_amount"))
    @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    private Price price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Product(String title, String description, Price price, List<String> images) {
        this.id = ProductId.generate();
        this.title = title;
        this.description = description;
        this.price = price;
        this.images = images != null ? images : new ArrayList<>();
        this.status = ProductStatus.DRAFT;
        this.createdAt = LocalDateTime.now();
    }

    public void publish() {
        this.status = ProductStatus.PUBLISHED;
    }
}
