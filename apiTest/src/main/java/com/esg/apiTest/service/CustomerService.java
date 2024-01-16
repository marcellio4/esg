package com.esg.apiTest.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.springframework.stereotype.Service;
import com.esg.apiTest.components.CustomerMapper;
import com.esg.apiTest.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerService {
    
    public CustomerMapper customerToMapper(Customer customer) {
       return CustomerMapper.builder()
        .customerRef(customer.getCustomerRef())
        .customerName(customer.getCustomerName())
        .addressLine1(customer.getAddressLine1())
        .addressLine2(customer.getAddressLine2())
        .Town(customer.getTown())
        .country(customer.getCountry())
        .county(customer.getCounty())
        .postcode(customer.getPostcode())
        .build();
    }

    public Customer mapperToCustomer(CustomerMapper mapper) {
        return Customer.builder()
        .customerRef(mapper.getCustomerRef())
        .customerName(mapper.getCustomerName())
        .addressLine1(mapper.getAddressLine1())
        .addressLine2(mapper.getAddressLine2())
        .Town(mapper.getTown())
        .country(mapper.getCountry())
        .county(mapper.getCounty())
        .postcode(mapper.getPostcode())
        .build(); 
    }

    public void saveCustomerRow(List<CustomerMapper> customers) {
        for(CustomerMapper mapper : customers) {
            try {
                sendPostRequest(mapper);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void sendPostRequest(CustomerMapper customer) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(customer);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create("http://localhost:8080/api/customer"))
                .header("Content-Type", "application/json")
                .build();

        HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }
}
