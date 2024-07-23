package com.beksons.doa;

import com.beksons.entities.Customer;
import com.beksons.entities.RentInfo;

import java.time.LocalDate;

public interface CustomerDao {

    String saveCustomer(Customer newCustomer);

    String createCustomer(Customer customer, RentInfo rentInfo);

    void rentHouse(Long clientId, Long houseId, Long agencyId, LocalDate rentDate);

    String deleteCustomer(Long id);








}
