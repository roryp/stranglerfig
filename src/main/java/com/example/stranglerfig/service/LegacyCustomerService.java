package com.example.stranglerfig.service;

import com.example.stranglerfig.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LegacyCustomerService {

    private List<Customer> sampleCustomers = Arrays.asList(
            new Customer("LEGACY_1", "Legacy Customer 1"),
            new Customer("LEGACY_2", "Legacy Customer 2"),
            new Customer("LEGACY_3", "Legacy Customer 3")
    );

    public Customer getCustomerById(String id) {
        // Retrieve customer from sample list
        return sampleCustomers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
