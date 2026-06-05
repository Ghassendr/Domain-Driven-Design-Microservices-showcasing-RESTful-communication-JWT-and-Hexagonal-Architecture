package com.example.product.domain.repository;

import com.example.product.domain.model.Email;
import com.example.product.domain.model.Supplier;

import java.util.Optional;

public interface SupplierRepository {
    Supplier save(Supplier supplier);

    Optional<Supplier> findByEmail(Email email);
}
