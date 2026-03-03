package com.uba.check_book.dto.branch;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponseDTO {
    private long id;
    private Integer solId;
    private String label;
    private String bankName;
}
