package com.example.product.interface_layer.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProductResponseDTO {
    String id;
    String title;
    String description;
    Double price;
    List<String> images;
}
