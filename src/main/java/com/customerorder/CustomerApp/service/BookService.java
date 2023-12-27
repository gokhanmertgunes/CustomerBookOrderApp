package com.customerorder.CustomerApp.service;

import com.customerorder.CustomerApp.model.Book;
import com.customerorder.CustomerApp.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService
{
    private final IBookRepository repository;
    public void addBook(String name, double price) {
        if(price < 0) throw new IllegalStateException("Price can not be less than 0");

        Optional<Book> b = repository.findById(name);
        Book newBook;
        if(b.isEmpty()) newBook = new Book(name,price, 1);
        else
        {
            newBook = b.get();
            newBook.setPrice(price);
            newBook.setStock(newBook.getStock() + 1);
        }
        repository.save(newBook);
    }

    public void updateBookCount(String name, int count) {

        Optional<Book> b = repository.findById(name);
        if(b.isEmpty()) throw new IllegalStateException("Book with name " + name + " does not exist.");

        Book book = b.get();
        if(book.getStock() + count < 0)
            throw new IllegalStateException("There is no enough book in stock.");

        book.setStock(book.getStock() + count);
        repository.save(book);
    }
}
