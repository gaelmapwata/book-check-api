package com.uba.check_book.dto.branch;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchCreateDTO {
    private Integer solId;
    private String label;
    private Long bankId;
}
