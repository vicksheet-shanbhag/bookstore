package com.example.bookstore.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GenericResponse {
    public String status;
    public String errorMessage;
    public Object data;
}
