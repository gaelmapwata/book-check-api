package com.uba.check_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String customerName;
    private double amount;
    private String currency;
    private String drAccountNumber;
    private String crAccountNumber;
    private String stan;
    private String tranDateTime;
    private long processingCode;
    private String countryCode;
    private String valueDate;
    private String reservedFld1;
    private String errorFinacle;
    private String error;
    private boolean success;
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="checker_id")
    private User checker;

    @OneToMany(mappedBy = "transaction")
    private List<CheckBook>checkBooks;

}
