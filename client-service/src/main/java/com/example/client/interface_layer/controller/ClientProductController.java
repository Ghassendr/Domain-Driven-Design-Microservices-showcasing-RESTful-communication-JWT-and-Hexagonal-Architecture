package com.example.client.interface_layer.controller;

import com.example.client.application.usecase.ListPublishedProductsUseCase;
import com.example.client.domain.model.ProductView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientProductController {

    private final ListPublishedProductsUseCase listPublishedProductsUseCase;

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<ProductView> products = listPublishedProductsUseCase.execute();
        model.addAttribute("products", products);
        return "products";
    }
}
