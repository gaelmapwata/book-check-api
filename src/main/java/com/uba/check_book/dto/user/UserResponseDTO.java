package com.uba.check_book.dto.user;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private boolean locked;
    private boolean is_validated;
    private int totalLoginAttempt;
    private String branchName;
    private List<String> roles;
}
