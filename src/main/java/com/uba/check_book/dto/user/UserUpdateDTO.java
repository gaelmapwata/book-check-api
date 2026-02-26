package com.uba.check_book.dto.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {
    private String email;
    private String password;
    private Long branchId;
    private List<Long> roleIds;
}
