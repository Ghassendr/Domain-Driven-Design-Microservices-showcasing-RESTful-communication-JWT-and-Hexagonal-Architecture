package com.example.product.application.usecase;

import com.example.product.domain.model.Email;
import com.example.product.domain.model.Supplier;
import com.example.product.domain.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterSupplierUseCase {

    private final SupplierRepository supplierRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Supplier register(String companyName, String emailStr, String password) {
        if (supplierRepository.findByEmail(new Email(emailStr)).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        String encodedPassword = passwordEncoder.encode(password);
        Supplier supplier = new Supplier(companyName, new Email(emailStr), encodedPassword);
        return supplierRepository.save(supplier);
    }
}
