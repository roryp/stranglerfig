package com.example.stranglerfig.service;

import com.example.stranglerfig.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class ModernCustomerService {

    public Customer getCustomerById(String id) {
        // Simulate fetching customer from modern system
        return new Customer(id, "Modern Customer");
    }
}
