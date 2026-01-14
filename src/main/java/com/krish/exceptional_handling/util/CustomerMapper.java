package com.krish.exceptional_handling.util;


import com.krish.exceptional_handling.dto.CustomerRequestDto;
import com.krish.exceptional_handling.dto.CustomerResponseDto;
import com.krish.exceptional_handling.dto.Gender;
import com.krish.exceptional_handling.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDto dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setGender(Gender.valueOf(dto.getGender()));
        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());
        customer.setState(dto.getState());
        customer.setCountry(dto.getCountry());
        customer.setPostalCode(dto.getPostalCode());
        return customer;
    }

    public static CustomerResponseDto toResponse(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setDateOfBirth(customer.getDateOfBirth());
        dto.setGender(customer.getGender().name());
        dto.setAddress(customer.getAddress());
        dto.setCity(customer.getCity());
        dto.setState(customer.getState());
        dto.setCountry(customer.getCountry());
        dto.setPostalCode(customer.getPostalCode());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        return dto;
    }
}
