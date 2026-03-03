package com.uba.check_book.dto.bank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponseDTO {
    private long id;
    private String bank_id;
    private String label;
    private String countryName;
}
