package com.customerorder.CustomerApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_order")
public class BookOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bookName;
    private int bookCount;
    private double bookPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
