package com.esg.apiTest.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esg.apiTest.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
 public Optional<Customer> findByCustomerRef(String customerRef);
}
