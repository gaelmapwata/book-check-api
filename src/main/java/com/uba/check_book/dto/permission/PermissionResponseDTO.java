package com.uba.check_book.dto.permission;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponseDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String resourceName;
}
