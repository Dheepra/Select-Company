package com.Select.Project.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @GetMapping("/customers")
    public CustomerError getAllCustomers() {
        return customerServices.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerError getCustomerById(@PathVariable Long id) {
        return customerServices.getCustomerById(id);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomerById(@PathVariable Long id) {
        return customerServices.deleteCustomerById(id);
    }

    @PostMapping("/customers")
    public CustomerError addCustomer(@RequestBody Customers customers) {
        return customerServices.addCustomer(customers);
    }

    @PutMapping("/customers/{id}")
    public CustomerError updateCustomerById(@PathVariable Long id, @RequestBody Customers customers) {
        return customerServices.updateCustomerById(id, customers);
    }

    
}
