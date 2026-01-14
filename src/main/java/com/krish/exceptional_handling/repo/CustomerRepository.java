package com.krish.exceptional_handling.repo;

import com.krish.exceptional_handling.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find by email
    Optional<Customer> findByEmail(String email);

    // Find customers by city
    List<Customer> findByCity(String city);

    // Find customers by country
    List<Customer> findByCountry(String country);

    // Check if email already exists
    boolean existsByEmail(String email);
}
