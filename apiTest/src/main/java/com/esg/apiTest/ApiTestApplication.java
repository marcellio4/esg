package com.esg.apiTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.esg.apiTest.service.CsvService;
import com.esg.apiTest.service.CustomerService;

@SpringBootApplication
public class ApiTestApplication implements CommandLineRunner {

	@Autowired
	private CsvService csvService;
	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(ApiTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerService.saveCustomerRow(csvService.convertCsvToList());
	}

}
