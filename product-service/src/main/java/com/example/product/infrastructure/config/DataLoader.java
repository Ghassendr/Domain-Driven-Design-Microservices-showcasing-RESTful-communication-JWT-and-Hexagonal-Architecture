package com.example.product.infrastructure.config;

import com.example.product.application.usecase.RegisterSupplierUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(RegisterSupplierUseCase registerSupplierUseCase) {
        return args -> {
            try {
                registerSupplierUseCase.register("Default Supplier", "admin@supplier.com", "password");
                System.out.println("Default supplier created: admin@supplier.com / password");
            } catch (Exception e) {
                // Ignore if already exists
            }
        };
    }
}
