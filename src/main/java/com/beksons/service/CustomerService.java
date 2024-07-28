package com.beksons.service;

import com.beksons.entities.Customer;
import com.beksons.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    String saveCustomer(Customer customer);

    String saveCustomerWithRentInfo(Customer customer , Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut);


    String assignRentInfoToCustomer(RentInfo rentInfo, Long customerId, Long houseId, Long agencyId);


    List<Customer> getAllCustomers();


    Optional<Customer> getCustomerById(Long customerId);


    String updateCustomer(Long customerId, Customer newCustomer);


    String deleteCustomer(Long customerId);


}
