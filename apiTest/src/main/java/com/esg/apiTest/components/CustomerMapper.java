package com.esg.apiTest.components;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMapper {
    private String customerRef;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String Town;
    private String county;
    private String country;
    private String postcode;
}
