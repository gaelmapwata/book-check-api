package com.uba.check_book.dto.user;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {
    private String email;
    private String password;
    private Long branchId;
    private List<Long> roleIds;

}
