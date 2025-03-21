package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "auther is required")
    private String author;

    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    @Column(unique = true)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowingRecord> borrowingRecords;

    private boolean available = true;
}
