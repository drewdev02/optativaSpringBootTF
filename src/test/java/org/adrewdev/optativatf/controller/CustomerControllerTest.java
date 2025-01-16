package org.adrewdev.optativatf.controller;

import org.adrewdev.optativatf.entity.CustomerEntity;
import org.adrewdev.optativatf.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private CustomerEntity customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new CustomerEntity();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
    }

    @Test
    void getAllCustomers() {
        List<CustomerEntity> customers = Arrays.asList(customer);
        when(customerService.getAllCustomers()).thenReturn(customers);

        var response = (ResponseEntity<List<CustomerEntity>>) customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getFirstName());
    }

    @Test
    void createCustomer() {
        when(customerService.saveCustomer(any(CustomerEntity.class))).thenReturn(customer);

        var response = (ResponseEntity<CustomerEntity>) customerController.createCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
    }

    @Test
    void updateCustomer() {
        when(customerService.updateCustomer(1L, customer)).thenReturn(customer);

        var response = (ResponseEntity<CustomerEntity>) customerController.updateCustomer(1L, customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
    }

    @Test
    void deleteCustomer() {
        doNothing().when(customerService).deleteCustomer(1L);

        var response = (ResponseEntity<Void>) customerController.deleteCustomer(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(1L);
    }
}
