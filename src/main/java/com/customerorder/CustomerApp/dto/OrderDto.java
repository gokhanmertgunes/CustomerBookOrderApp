package com.customerorder.CustomerApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderDto
{
    private String customerMail;
    private List<BookOrderDto> bookOrders;
}
