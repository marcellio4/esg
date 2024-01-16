package com.esg.apiTest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.esg.apiTest.components.CustomerMapper;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsvService {

    private final ResourceLoader resourceLoader;
    
    public List<CustomerMapper> convertCsvToList() {
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("Customer Ref", "customerRef");
        mapping.put("Customer Name", "customerName");
        mapping.put("Address Line 1", "addressLine1");
        mapping.put("Address Line 2", "addressLine2");
        mapping.put("Town", "Town");
        mapping.put("County", "county");
        mapping.put("Country", "country");
        mapping.put("Postcode", "postcode");
 
        HeaderColumnNameTranslateMappingStrategy<CustomerMapper> strategy =
             new HeaderColumnNameTranslateMappingStrategy<CustomerMapper>();
        strategy.setType(CustomerMapper.class);
        strategy.setColumnMapping(mapping);
 
        CSVReader csvReader = null;
        try {
            File file = resourceLoader.getResource("classpath:customerData.csv").getFile();
            csvReader = new CSVReader(new FileReader(file));
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CsvToBean<CustomerMapper> csvToBean = new CsvToBean<CustomerMapper>();

       return csvToBean.parse(strategy, csvReader);
    }
}
