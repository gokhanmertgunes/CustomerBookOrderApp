package com.customerorder.CustomerApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_order")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String customerMail;
    private LocalDate date;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<BookOrder> bookOrders = new ArrayList<>();

}
