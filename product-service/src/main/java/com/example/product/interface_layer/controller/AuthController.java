package com.example.product.interface_layer.controller;

import com.example.product.application.usecase.LoginSupplierUseCase;
import com.example.product.application.usecase.RegisterSupplierUseCase;
import com.example.product.domain.model.Supplier;
import com.example.product.domain.model.Email;
import com.example.product.domain.repository.SupplierRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final LoginSupplierUseCase loginSupplierUseCase;
    private final RegisterSupplierUseCase registerSupplierUseCase;
    private final SupplierRepository supplierRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            String token = loginSupplierUseCase.login(email, password);
            session.setAttribute("jwt", token);
            session.setAttribute("supplierEmail", email);

            supplierRepository.findByEmail(new Email(email)).ifPresent(supplier -> {
                session.setAttribute("supplierName", supplier.getDisplayName());
                session.setAttribute("supplierInitial", supplier.getDisplayName().substring(0, 1).toUpperCase());
            });

            return "redirect:/products/create";
        } catch (Exception e) {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String companyName, @RequestParam String email,
            @RequestParam String password, org.springframework.ui.Model model) {
        try {
            registerSupplierUseCase.register(companyName, email, password);
            return "redirect:/login?registered=true";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
