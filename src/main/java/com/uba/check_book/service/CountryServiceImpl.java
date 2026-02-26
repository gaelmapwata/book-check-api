package com.uba.check_book.service;

import com.uba.check_book.dto.CountryDTO;
import com.uba.check_book.entity.Country;
import com.uba.check_book.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryDTO> findAll(){
        return countryRepository.findAll()
                .stream()
                .map(c -> new CountryDTO(
                        c.getId(),
                        c.getLabel(),
                        c.getCode2(),
                        c.getCode3(),
                        c.getTelPrefixNum()))
                .collect(Collectors.toList());
    }

    @Override
    public CountryDTO save(CountryDTO countryDTO){
        Country country = new Country();
        country.setLabel(countryDTO.getLabel());
        country.setCode2(countryDTO.getCode2());
        country.setCode3(countryDTO.getCode3());
        country.setTelPrefixNum(countryDTO.getTelPrefixNum());

        Country saved = countryRepository.save(country);

        return new CountryDTO(
                saved.getId(),
                saved.getLabel(),
                saved.getCode2(),
                saved.getCode3(),
                saved.getTelPrefixNum());
    }

    @Override
    public CountryDTO update(Long id, CountryDTO countryDTO){
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        country.setLabel(countryDTO.getLabel());
        country.setCode2(countryDTO.getCode2());
        country.setCode3(countryDTO.getCode3());
        country.setTelPrefixNum(countryDTO.getTelPrefixNum());

        Country updated = countryRepository.save(country);

        return new CountryDTO(
                updated.getId(),
                updated.getLabel(),
                updated.getCode2(),
                updated.getCode3(),
                updated.getTelPrefixNum());
    }

    @Override
    public CountryDTO findById(Long id){
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        return new CountryDTO(
                country.getId(),
                country.getLabel(),
                country.getCode2(),
                country.getCode3(),
                country.getTelPrefixNum());
    }

    @Override
    public void deleteById(Long id){
        countryRepository.deleteById(id);
    }
}