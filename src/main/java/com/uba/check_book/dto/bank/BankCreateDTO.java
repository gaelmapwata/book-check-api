package com.uba.check_book.dto.bank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankCreateDTO {
    private String label;
    private String bank_id;
    private Long countryId;
}
