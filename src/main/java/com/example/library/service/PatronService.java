package com.example.library.service;

import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatronService {
    private final PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Optional<Patron> updatePatron(Long id, Patron patronDetails) {
        return patronRepository.findById(id).map(patron -> {
            patron.setName(patronDetails.getName());
            patron.setEmail(patronDetails.getEmail());
            return patronRepository.save(patron);
        });
    }


    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
