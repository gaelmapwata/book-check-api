package com.uba.check_book.dto.transaction;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private long id;
    private String customerName;
    private double amount;
    private String currency;
    private String drAccountNumber;
    private String crAccountNumber;
    private String errorFinacle;
    private String error;
    private boolean success;
    private String userName;
    private String checkerName;
}
