package com.example.bookstore.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookDTO {
    private String author;
    private String title;
    private String category;
    private String price;
    private int totalCount;
    private int sold;

}
