package com.example.product.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "suppliers")
@Getter
@NoArgsConstructor
public class Supplier {

    @Id
    private UUID id;

    @Column(name = "company_name")
    private String companyName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", unique = true, nullable = false))
    private Email email;

    @Column(nullable = false)
    private String passwordHash;

    public Supplier(String companyName, Email email, String passwordHash) {
        this.id = UUID.randomUUID();
        this.companyName = companyName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getDisplayName() {
        return companyName != null && !companyName.isEmpty() ? companyName : email.getValue();
    }
}
