package com.example.stranglerfig.service;

import com.example.stranglerfig.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class LegacyCustomerService {

    public Customer getCustomerById(String id) {
        // Simulate fetching customer from legacy system
        return new Customer(id, "Legacy Customer");
    }
}
