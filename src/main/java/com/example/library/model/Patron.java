package com.example.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "you must input valid email")
    @Column(unique = true)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    private List<BorrowingRecord> borrowingRecords;


}
