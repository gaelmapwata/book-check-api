package com.uba.check_book.controller;

import com.uba.check_book.dto.CountryDTO;
import com.uba.check_book.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('COUNTRY:READ')")
    public List<CountryDTO> getAllCountries(){
        return countryService.findAll();
    }

    @GetMapping("/id")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('COUNTRY:READ')")
    public CountryDTO getCountryById(@PathVariable  Long id){
        return countryService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('COUNTRY:CREATE')")
    public CountryDTO createCountry(@RequestBody  CountryDTO countryDTO){
        return countryService.save(countryDTO);
    }

    @PutMapping("/id")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('COUNTRY:UPDATE')")
    public CountryDTO updateCountry(@PathVariable Long id,@RequestBody CountryDTO countryDTO){
        return countryService.update(id,countryDTO);
    }

    @DeleteMapping("/id")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('COUNTRY:DELETE')")
    public void deleteCountry(@PathVariable Long id){
        countryService.deleteById(id);
    }
}
