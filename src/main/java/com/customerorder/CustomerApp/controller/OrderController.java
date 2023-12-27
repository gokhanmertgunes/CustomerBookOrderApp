package com.customerorder.CustomerApp.controller;

import com.customerorder.CustomerApp.dto.OrderDto;
import com.customerorder.CustomerApp.model.Order;
import com.customerorder.CustomerApp.service.JwtService;
import com.customerorder.CustomerApp.service.OrderService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewOrder(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        String customerMail = jwtService.extractCustomerEmail(request.getHeader("Authorization").substring(7));
        orderDto.setCustomerMail(customerMail);
        List<String> notOrdered;
        try
        {
            notOrdered = orderService.addNewOrder(orderDto);
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
        if(notOrdered.isEmpty()) return ResponseEntity.ok("Successfully added new order.");
        else return ResponseEntity.ok("New order added:\n" + notOrdered);
    }
}
