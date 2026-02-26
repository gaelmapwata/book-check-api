package com.uba.check_book.dto.checkBook;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckBookCreateDTO {
    private long id;
    private String company;
    private long numberCheckBook;
    private long numberOfLeaves;
    private String status;
    private long serialNumberFrom;
    private long seralNumberTo;
    private long transactionId;
}
