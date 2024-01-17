package com.esg.apiTest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.esg.apiTest.components.CustomerMapper;
import com.esg.apiTest.model.Customer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;
    
    @Test
    void customerToMapper() {
        var customer = Customer.builder()
            .Town("Town")
            .addressLine1("24 address")
            .country("United Kingdom")
            .county("Essex")
            .customerName("Test name")
            .customerRef("ref")
            .postcode("RT4 6GH")
            .build();
        var result = service.customerToMapper(customer);
        assertThat(result, isA(CustomerMapper.class));
        assertThat(result.getCustomerName(), is("Test name"));
    }
    
    @Test
    void mapperToCustomer() {
        var customer = CustomerMapper.builder()
            .Town("Town")
            .addressLine1("24 address")
            .country("United Kingdom")
            .county("Essex")
            .customerName("Test name")
            .customerRef("ref")
            .postcode("RT4 6GH")
            .build();
        var result = service.mapperToCustomer(customer);
        assertThat(result, isA(Customer.class));
        assertThat(result.getCustomerName(), is("Test name"));
    }
}
