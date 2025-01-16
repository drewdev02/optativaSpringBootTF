package org.adrewdev.optativatf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adrewdev.optativatf.entity.CustomerEntity;
import org.adrewdev.optativatf.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public CustomerEntity updateCustomer(Long customerId, CustomerEntity customer) {
        var olCustomer = customerRepository.findById(customerId);
        if (olCustomer.isEmpty()) {
            log.error("Customer not found");
            throw new RuntimeException("Customer not found");
        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

}
