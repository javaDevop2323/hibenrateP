package com.beksons.service.serviceImpl;

import com.beksons.doa.CustomerDao;
import com.beksons.doa.daoImpl.CustomerDaoImpl;
import com.beksons.entities.Customer;
import com.beksons.entities.RentInfo;
import com.beksons.service.CustomerService;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
   private final CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public String saveCustomer(Customer customer) {
     return  customerDao.saveCustomer(customer);

    }

    @Override
    public String saveCustomerWithRentInfo(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        return customerDao.saveCustomerWithRentInfo(customer,houseId,agencyId,checkIn,checkOut);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public String updateCustomer(Long customerId, Customer newCustomer) {
        return customerDao.updateCustomer(customerId,newCustomer);
    }

    @Override
    public String deleteCustomer(Long customerId) {
        return customerDao.deleteCustomer(customerId);
    }
}
