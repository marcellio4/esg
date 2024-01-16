package com.esg.apiTest.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "customerRef"))
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String customerRef;
    @NotBlank
    @Column(nullable = false)
    private String customerName;
    @NotBlank
    @Column(nullable = false)
    private String addressLine1;
    private String addressLine2;
    @NotBlank
    @Column(nullable = false)
    private String Town;
    @NotBlank
    @Column(nullable = false)
    private String county;
    @NotBlank
    @Column(nullable = false)
    private String country;
    @NotBlank
    @Column(nullable = false)
    private String postcode;
}
