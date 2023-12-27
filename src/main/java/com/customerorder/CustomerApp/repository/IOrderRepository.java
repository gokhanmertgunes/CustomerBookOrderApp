package com.customerorder.CustomerApp.repository;

import com.customerorder.CustomerApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer>
{
}
