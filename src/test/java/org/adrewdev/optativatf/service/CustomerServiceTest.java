package org.adrewdev.optativatf.service;

import org.adrewdev.optativatf.entity.CustomerEntity;
import org.adrewdev.optativatf.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerEntity customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new CustomerEntity();
        customer.setId(1L);
        customer.setFirstName("John Doe");
        customer.setEmail("john.doe@example.com");
    }

    @Test
    void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        var result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getFirstName());
    }

    @Test
    void saveCustomer() {
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customer);

        var result = customerService.saveCustomer(customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getFirstName());
    }

    @Test
    void updateCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customer);

        var result = customerService.updateCustomer(1L, customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getFirstName());
    }

    @Test
    void updateCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> customerService.updateCustomer(1L, customer));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void deleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }
}
