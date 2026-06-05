package com.example.product.interface_layer.controller;

import com.example.product.application.usecase.CreateProductUseCase;
import com.example.product.application.usecase.PublishProductUseCase;
import com.example.product.domain.model.Product;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final PublishProductUseCase publishProductUseCase;

    @GetMapping("/create")
    public String createProductPage(HttpSession session, Model model) {
        if (session.getAttribute("jwt") == null) {
            return "redirect:/login";
        }
        model.addAttribute("supplierEmail", session.getAttribute("supplierEmail"));
        model.addAttribute("supplierName", session.getAttribute("supplierName"));
        model.addAttribute("supplierInitial", session.getAttribute("supplierInitial"));
        return "create_product";
    }

    @PostMapping("/create")
    public String createProduct(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam String images,
            @RequestParam String action,
            HttpSession session) {

        if (session.getAttribute("jwt") == null) {
            return "redirect:/login";
        }

        List<String> imageList = Arrays.asList(images.split(","));
        Product product = createProductUseCase.createProduct(title, description, price, imageList);

        if ("publish".equals(action)) {
            publishProductUseCase.publishProduct(product.getId().getValue());
        }

        return "redirect:/products/create?success=true";
    }
}
