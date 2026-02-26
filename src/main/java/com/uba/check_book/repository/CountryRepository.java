package com.uba.check_book.repository;

import com.uba.check_book.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Optional<Country> findByCode2(String cd);
}
