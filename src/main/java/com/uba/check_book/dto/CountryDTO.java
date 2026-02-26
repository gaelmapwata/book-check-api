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
public class CountryDTO {
    private long id;
    private String label;
    private String code2;
    private String code3;
    private String telPrefixNum;
}
