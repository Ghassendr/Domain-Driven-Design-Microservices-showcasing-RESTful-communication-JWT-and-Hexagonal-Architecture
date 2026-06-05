package com.example.product.infrastructure.persistence;

import com.example.product.domain.model.Email;
import com.example.product.domain.model.Supplier;
import com.example.product.domain.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SupplierRepositoryAdapter implements SupplierRepository {

    private final JpaSupplierRepository jpaSupplierRepository;

    @Override
    public Supplier save(Supplier supplier) {
        return jpaSupplierRepository.save(supplier);
    }

    @Override
    public Optional<Supplier> findByEmail(Email email) {
        return jpaSupplierRepository.findByEmail(email);
    }
}
