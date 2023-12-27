package com.customerorder.CustomerApp.controller;

import com.customerorder.CustomerApp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController
{
    private final BookService bookService;
    @PostMapping(path = "/add")
    public ResponseEntity<String> addBook(@RequestParam("name") String name, @RequestParam("price") double price) {
        try
        {
            bookService.addBook(name, price);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("Success");
    }
    @PostMapping(path = "/update")
    public ResponseEntity<String> updateBook(@RequestParam("name") String name, @RequestParam("count") int count) {
        try
        {
            bookService.updateBookCount(name,count);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("Success");
    }
}
