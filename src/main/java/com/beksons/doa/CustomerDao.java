package com.beksons.doa;

import com.beksons.entities.Customer;
import com.beksons.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    String saveCustomer(Customer customer);

    String saveCustomerWithRentInfo(Customer customer , Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut);


    List<Customer> getAllCustomers();


    Optional<Customer> getCustomerById(Long customerId);


    String updateCustomer(Long customerId, Customer newCustomer);


    String deleteCustomer(Long customerId);

    List<Customer> sortByName(String ascOrDesc);






}
