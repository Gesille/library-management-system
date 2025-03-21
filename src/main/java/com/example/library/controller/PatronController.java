package com.example.library.controller;

import com.example.library.dto.PatronResponse;
import com.example.library.exception.CustomException;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        return ResponseEntity.ok(patronService.getAllPatrons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronResponse> getPatronById(@PathVariable Long id) {
        Patron patron = patronService.getPatronById(id)
                .orElseThrow(() -> new CustomException("Patron not found", 404));

        return ResponseEntity.ok(new PatronResponse("Patron found successfully", patron));
    }

    @PostMapping
    public ResponseEntity<PatronResponse> addPatron(@Valid @RequestBody Patron patron) {
        Patron savedPatron = patronService.addPatron(patron);
        return ResponseEntity.ok(new PatronResponse("Patron registered successfully", savedPatron));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronResponse> updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patronDetails) {
        Patron updatedPatron = patronService.updatePatron(id, patronDetails)
                .orElseThrow(() -> new CustomException("Patron not found", 404));

        return ResponseEntity.ok(new PatronResponse("Patron updated successfully", updatedPatron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
