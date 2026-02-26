package com.uba.check_book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
    private long id;
    private String slug;
    private String name;
    private String description;
    private String resourceName;
    private List<String> roleNames;
}
