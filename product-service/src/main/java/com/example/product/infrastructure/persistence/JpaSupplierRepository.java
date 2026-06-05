package com.example.product.infrastructure.persistence;

import com.example.product.domain.model.Email;
import com.example.product.domain.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaSupplierRepository extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findByEmail(Email email);
}
