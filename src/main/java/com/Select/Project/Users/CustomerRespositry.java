package com.Select.Project.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRespositry extends JpaRepository<Customers, Integer> {
    Customers findByCustomerId(Long customerId);
    void deleteByCustomerId(Long customerId);
}
