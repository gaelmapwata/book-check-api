package com.uba.check_book.service;

import com.uba.check_book.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    List<CountryDTO> findAll();
    CountryDTO save(CountryDTO countryDTO);
    CountryDTO update(Long id, CountryDTO countryDTO);
    CountryDTO findById(Long id);
    void deleteById(Long id);
}
