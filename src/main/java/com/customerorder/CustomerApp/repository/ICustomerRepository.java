package com.customerorder.CustomerApp.repository;

import com.customerorder.CustomerApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Integer>
{
    Optional<Customer> findByEmail(String email);
}
