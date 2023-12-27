package com.customerorder.CustomerApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookOrderDto
{
    private String bookName;
    private int bookCount;
}
