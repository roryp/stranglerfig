package com.example.stranglerfig.controller;

import com.example.stranglerfig.model.Customer;
import com.example.stranglerfig.service.LegacyCustomerService;
import com.example.stranglerfig.service.ModernCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerRouterController {

    private final LegacyCustomerService legacyService;
    private final ModernCustomerService modernService;

    @Autowired
    public CustomerRouterController(LegacyCustomerService legacyService, ModernCustomerService modernService) {
        this.legacyService = legacyService;
        this.modernService = modernService;
    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomer(@RequestParam String id) {
        if (isModernCustomer(id)) {
            Customer customer = modernService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        }
        Customer customer = legacyService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    private boolean isModernCustomer(String id) {
        // Logic to determine if the request should be routed to the modern service
        return id.startsWith("MODERN_");
    }
}
