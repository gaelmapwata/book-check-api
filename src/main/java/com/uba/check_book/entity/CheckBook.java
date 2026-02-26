package com.uba.check_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "check_books")
public class CheckBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String company;
    private long numberCheckBook;
    private long numberOfLeaves;
    private String status;
    private long serialNumberFrom;
    private long seralNumberTo;

    @ManyToOne
    @JoinColumn(name="transaction_id", nullable = false)
    private Transaction transaction;
}
