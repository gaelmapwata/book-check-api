package com.uba.check_book.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;
    private String code2;
    private String code3;

    @Column(unique = true)
    private String telPrefixNum;
    private LocalDateTime deleteAt;

    @OneToMany(mappedBy = "country")
    private List<Bank> banks;

}
