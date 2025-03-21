package com.example.library.dto;

import com.example.library.model.BorrowingRecord;

public class BorrowingResponse {
    private String message;
    private BorrowingRecord borrowingRecord;

    public BorrowingResponse(String message, BorrowingRecord borrowingRecord) {
        this.message = message;
        this.borrowingRecord = borrowingRecord;
    }

    public String getMessage() { return message; }
    public BorrowingRecord getBorrowingRecord() { return borrowingRecord; }
}
