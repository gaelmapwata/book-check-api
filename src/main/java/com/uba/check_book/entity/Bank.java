package com.uba.check_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    @Column(unique = true)
    private String bankId;
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name="country_id", nullable = false)
    Country country;

    @OneToMany(mappedBy = "bank")
    private List<Branch>branches;
}
