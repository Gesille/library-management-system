package com.example.library.controller;

import com.example.library.exception.CustomException;
import com.example.library.model.BorrowingRecord;
import com.example.library.service.BorrowingService;
import com.example.library.dto.BorrowingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingResponse> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord borrowingRecord = borrowingService.borrowBook(bookId, patronId);
        return ResponseEntity.ok(new BorrowingResponse("Book borrowed successfully", borrowingRecord));
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord borrowingRecord = borrowingService.returnBook(bookId, patronId);
        return ResponseEntity.ok(new BorrowingResponse("Book returned successfully", borrowingRecord));
    }
}
