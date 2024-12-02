package com.example.stranglerfig.service;

import com.example.stranglerfig.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ModernCustomerService {

    private List<Customer> sampleCustomers = Arrays.asList(
            new Customer("MODERN_1", "Modern Customer 1"),
            new Customer("MODERN_2", "Modern Customer 2"),
            new Customer("MODERN_3", "Modern Customer 3")
    );

    public Customer getCustomerById(String id) {
        // Retrieve customer from sample list
        return sampleCustomers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
