package com.customerorder.CustomerApp.repository;

import com.customerorder.CustomerApp.model.Book;
import com.customerorder.CustomerApp.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,String>
{
    Book findByName(String name);

    void deleteByName(String name);

}
