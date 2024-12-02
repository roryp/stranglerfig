package com.example.stranglerfig.controller;

import com.example.stranglerfig.model.Customer;
import com.example.stranglerfig.service.LegacyCustomerService;
import com.example.stranglerfig.service.ModernCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerRouterControllerTest {

    @Mock
    private LegacyCustomerService legacyService;

    @Mock
    private ModernCustomerService modernService;

    @InjectMocks
    private CustomerRouterController customerRouterController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomer_ModernCustomer() {
        String modernCustomerId = "MODERN_1";
        Customer modernCustomer = new Customer(modernCustomerId, "Modern Customer 1");

        when(modernService.getCustomerById(modernCustomerId)).thenReturn(modernCustomer);

        ResponseEntity<Customer> response = customerRouterController.getCustomer(modernCustomerId);

        assertEquals(ResponseEntity.ok(modernCustomer), response);
    }

    @Test
    public void testGetCustomer_LegacyCustomer() {
        String legacyCustomerId = "LEGACY_1";
        Customer legacyCustomer = new Customer(legacyCustomerId, "Legacy Customer 1");

        when(legacyService.getCustomerById(legacyCustomerId)).thenReturn(legacyCustomer);

        ResponseEntity<Customer> response = customerRouterController.getCustomer(legacyCustomerId);

        assertEquals(ResponseEntity.ok(legacyCustomer), response);
    }
}
