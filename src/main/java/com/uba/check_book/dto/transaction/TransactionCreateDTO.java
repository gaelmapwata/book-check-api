package com.uba.check_book.dto.transaction;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCreateDTO {
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
    private long userId;
    private long checkerId;
}
