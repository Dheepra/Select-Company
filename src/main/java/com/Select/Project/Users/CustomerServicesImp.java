package com.Select.Project.Users;

import java.util.List;
import java.util.regex.Pattern;
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

        List<Customers> customersList = customerRespositry.findAll();
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); 
        Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^[0-9]{10}$");
       
        if (customers.getCustomerName() == null || customers.getCustomerEmail() == null || customers.getCustomerPhone() == null) {
            return new CustomerError(HttpStatus.BAD_REQUEST.value(), "Customer name, email, and phone are required", null);
        }
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(customers.getCustomerEmail()).find()) {
            return new CustomerError(HttpStatus.BAD_REQUEST.value(), "Invalid email address", null);
        }
        
        if (!VALID_PHONE_NUMBER_REGEX.matcher(customers.getCustomerPhone()).find() || customers.getCustomerPhone().length() != 10) {
            return new CustomerError(HttpStatus.BAD_REQUEST.value(), "Invalid phone number", null);
        }
        for (Customers customer : customersList) {
            if (customer.getCustomerEmail().equals(customers.getCustomerEmail()) || customer.getCustomerPhone().equals(customers.getCustomerPhone())) {
                return new CustomerError(HttpStatus.BAD_REQUEST.value(), "Customer already exists", List.of(customer));
            }
        }
        customerRespositry.save(customers);
        return new CustomerError(HttpStatus.CREATED.value(), "Customer added successfully", List.of(customers));
    }
}
