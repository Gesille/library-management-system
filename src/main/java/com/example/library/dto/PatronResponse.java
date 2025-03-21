package com.example.library.dto;

import com.example.library.model.Patron;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatronResponse {
    private String message;
    private Patron patron;
}
