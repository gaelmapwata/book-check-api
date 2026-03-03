package com.uba.check_book.dto.bank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankUpdateDTO {
    private String label;
    private String bank_id;
    private Long countryId;
}
