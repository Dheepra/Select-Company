package com.Select.Project.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServicesImp implements CustomerServices {

    @Autowired
    private CustomerRespositry customerRespositry;

    @Override
    public CustomerError getAllCustomers() {
        List<Customers> customers = customerRespositry.findAll();
        if (customers.isEmpty()) {
           return new CustomerError(HttpStatus.NOT_FOUND.value(), "No customers found", customers);
        }
        return new CustomerError(HttpStatus.OK.value(), "Customers fetched successfully", customers);
    }

    @Override
    public CustomerError getCustomerById(Long id) {
        Customers customer = customerRespositry.findByCustomerId(id);
        if (customer == null) {
            return new CustomerError(HttpStatus.NOT_FOUND.value(), "Customer not found", null);
        }
        return new CustomerError(HttpStatus.OK.value(), "Customer fetched successfully", List.of(customer));
    }

    @Override
    @Transactional
    public String deleteCustomerById(Long id) {
        Customers customer = customerRespositry.findByCustomerId(id);
        if (customer == null) {
            return "Customer not found";
        }
        customerRespositry.deleteByCustomerId(id);
        return "Customer deleted successfully";
    }

    @Override
    public CustomerError addCustomer(Customers customers) {
        customerRespositry.save(customers);
        return new CustomerError(HttpStatus.CREATED.value(), "Customer added successfully", List.of(customers));
    }
}
