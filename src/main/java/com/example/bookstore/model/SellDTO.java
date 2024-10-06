package com.example.bookstore.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SellDTO {
    private Long bookId;
    private int sellBookCount;

}
