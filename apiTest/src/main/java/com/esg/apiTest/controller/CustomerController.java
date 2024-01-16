package com.esg.apiTest.controller;

import java.security.InvalidParameterException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esg.apiTest.components.CustomerMapper;
import com.esg.apiTest.model.Customer;
import com.esg.apiTest.repository.CustomerRepository;
import com.esg.apiTest.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;
    private final CustomerRepository repository;
    
    @PostMapping("/customer")
    public void saveCustomer(@RequestBody CustomerMapper customerMapper) {
        Customer customer = service.mapperToCustomer(customerMapper);
        repository.save(customer);
    }

    @GetMapping("/customer/info/{customerRef}")
    public ResponseEntity<CustomerMapper> getCustomerInfo(@PathVariable String customerRef) throws Exception {
        if(!StringUtils.hasText(customerRef)){
            throw new InvalidParameterException("Customer ref is missing!");
        }
        Customer customer = repository.findByCustomerRef(customerRef)
            .orElseThrow(() -> new InvalidParameterException(String.format("Customer not found by ref: %s", customerRef)));
        return ResponseEntity.ok().body(service.customerToMapper(customer));
    }
}
