package com.customerorder.CustomerApp.repository;

import com.customerorder.CustomerApp.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookOrderRepository extends JpaRepository<BookOrder, Integer>
{
}
