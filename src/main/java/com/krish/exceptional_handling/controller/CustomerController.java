package com.krish.exceptional_handling.controller;

import com.krish.exceptional_handling.dto.CustomerRequestDto;
import com.krish.exceptional_handling.dto.CustomerResponseDto;
import com.krish.exceptional_handling.entity.Customer;
import com.krish.exceptional_handling.repo.CustomerRepository;
import com.krish.exceptional_handling.util.CustomerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
             @RequestBody CustomerRequestDto requestDto) {

        Customer customer = CustomerMapper.toEntity(requestDto);
        Customer savedCustomer = customerRepository.save(customer);

        return new ResponseEntity<>(
                CustomerMapper.toResponse(savedCustomer),
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return CustomerMapper.toResponse(customer);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
