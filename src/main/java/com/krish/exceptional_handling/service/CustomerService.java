package com.krish.exceptional_handling.service;

import com.krish.exceptional_handling.dto.CustomerResponseDto;
import com.krish.exceptional_handling.entity.Customer;
import com.krish.exceptional_handling.exceptions.CustomerAlreadyExistsException;
import com.krish.exceptional_handling.exceptions.CustomerNotFoundException;
import com.krish.exceptional_handling.repo.CustomerRepository;
import com.krish.exceptional_handling.util.CustomerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        boolean exist = customerRepository.existsByEmail(customer.getEmail());
        if (exist) {
            throw new CustomerAlreadyExistsException(
                    "Customer already exists with email: " + customer.getEmail()
            );
        }
        return customerRepository.save(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
