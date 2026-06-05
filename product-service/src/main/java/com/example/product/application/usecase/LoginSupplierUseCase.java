package com.example.product.application.usecase;

import com.example.product.domain.model.Email;
import com.example.product.domain.model.Supplier;
import com.example.product.domain.repository.SupplierRepository;
import com.example.product.infrastructure.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginSupplierUseCase {

    private final SupplierRepository supplierRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public String login(String emailStr, String password) {
        Supplier supplier = supplierRepository.findByEmail(new Email(emailStr))
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(password, supplier.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtProvider.createToken(emailStr);
    }
}
