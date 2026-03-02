package com.uba.check_book.dto.permission;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionCreateDTO {
    private String slug;
    private String name;
    private String description;
    private Long resourceId;
}
