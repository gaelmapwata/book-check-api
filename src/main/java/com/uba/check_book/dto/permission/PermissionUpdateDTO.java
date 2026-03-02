package com.uba.check_book.dto.permission;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionUpdateDTO {
    private String slug;
    private String name;
    private String description;
    private Long resourceId;
}
