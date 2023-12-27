package com.customerorder.CustomerApp.service;

import com.customerorder.CustomerApp.dto.BookOrderDto;
import com.customerorder.CustomerApp.dto.OrderDto;
import com.customerorder.CustomerApp.model.Book;
import com.customerorder.CustomerApp.model.BookOrder;
import com.customerorder.CustomerApp.model.Order;
import com.customerorder.CustomerApp.repository.IBookOrderRepository;
import com.customerorder.CustomerApp.repository.IBookRepository;
import com.customerorder.CustomerApp.repository.ICustomerRepository;
import com.customerorder.CustomerApp.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IBookRepository bookRepository;
    public List<String> addNewOrder(OrderDto orderDto) {
        if(customerRepository.findByEmail(orderDto.getCustomerMail()).isEmpty()) throw new IllegalStateException("Customer does not exists.");
        Order order = new Order();
        order.setCustomerMail(orderDto.getCustomerMail());

        List<BookOrder> bookOrders = new ArrayList<>();
        List<String> bookNotOrdered = new ArrayList<>();
        Book book;
        for (BookOrderDto bookOrderDto : orderDto.getBookOrders())
        {
            if (bookRepository.findByName(bookOrderDto.getBookName()) == null)
                bookNotOrdered.add("Book with book name " + bookOrderDto.getBookName() + " does not exists in library.");
            else if (bookRepository.findByName(bookOrderDto.getBookName()).getStock() - bookOrderDto.getBookCount() < 0)
                bookNotOrdered.add("Book with book name " + bookOrderDto.getBookName() + " does not have enough stock. Current stock about that book is : " + bookRepository.findByName(bookOrderDto.getBookName()).getStock());
            else
            {
                book = bookRepository.findByName(bookOrderDto.getBookName());
                book.setStock(book.getStock() - bookOrderDto.getBookCount());
                if(book.getStock() == 0)
                    bookRepository.deleteByName(book.getName());

                BookOrder bookOrder = BookOrder.builder().bookName(bookOrderDto.getBookName()).bookCount(bookOrderDto.getBookCount()).order(order).build();
                bookOrders.add(bookOrder);
            }
        }
        order.setBookOrders(bookOrders);
        order.setDate(LocalDate.now());
        orderRepository.save(order);
        return bookNotOrdered;
    }
}
