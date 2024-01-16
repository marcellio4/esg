package com.esg.apiTest.controller;

import static org.mockito.Mockito.when;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.esg.apiTest.components.CustomerMapper;
import com.esg.apiTest.model.Customer;
import com.esg.apiTest.repository.CustomerRepository;
import com.esg.apiTest.service.CsvService;
import com.esg.apiTest.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.security.InvalidParameterException;
import org.assertj.core.api.Assertions;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private CustomerRepository repository;
    @MockBean
    private CsvService csvService;

    @Test
    void testSaveCustomer() throws Exception {
        CustomerMapper customerMapper = getCustomerMapper();
        Customer customer = getCustomer();
        
        when(customerService.mapperToCustomer(customerMapper)).thenReturn(customer);
        when(repository.save(customer)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerMapper)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void testGetCustomer() throws Exception {
        CustomerMapper customerMapper = getCustomerMapper();
        Customer customer = getCustomer();
        
        when(customerService.customerToMapper(customer)).thenReturn(customerMapper);
        when(repository.findByCustomerRef("345678")).thenReturn(Optional.of(customer));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/info/345678")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.customerName", Matchers.is("Test name")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.customerRef", Matchers.is("345678")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.town", Matchers.is("town")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.county", Matchers.is("Essex")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.country", Matchers.is("United Kingdom")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.postcode", Matchers.is("CM3 4GH")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine1", Matchers.is("24 address")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine2", Matchers.nullValue()));
    }
    
    @Test
    void testGetCustomerWhenMissingRefNumber() throws Exception {     
        when(repository.findByCustomerRef("3456")).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
            () -> mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/info/3456").contentType(MediaType.APPLICATION_JSON)))
        .hasCauseInstanceOf(InvalidParameterException.class).hasMessageContaining("Customer not found by ref: 3456");
    }

    private CustomerMapper getCustomerMapper() {
        return CustomerMapper.builder()
        .Town("town")
        .addressLine1("24 address")
        .addressLine2(null)
        .country("United Kingdom")
        .county("Essex")
        .customerName("Test name")
        .customerRef("345678")
        .postcode("CM3 4GH")
        .build();
    }
    
    private Customer getCustomer() {
        return Customer.builder()
        .Town("town")
        .addressLine1("24 address")
        .addressLine2(null)
        .country("United Kingdom")
        .county("Essex")
        .customerName("Test name")
        .customerRef("345678")
        .postcode("CM3 4GH")
        .build();
    }
}
