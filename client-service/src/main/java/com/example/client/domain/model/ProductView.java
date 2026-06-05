package com.example.client.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProductView {
    String id;
    String title;
    String description;
    Price price;
    List<String> images;
}
