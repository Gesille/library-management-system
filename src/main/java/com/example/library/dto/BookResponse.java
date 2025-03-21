package com.example.library.dto;

import com.example.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponse {
    private String message;
    private Book book;
}
