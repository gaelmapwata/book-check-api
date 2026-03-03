package com.uba.check_book.mapper;

import com.uba.check_book.dto.bank.BankCreateDTO;
import com.uba.check_book.dto.bank.BankResponseDTO;
import com.uba.check_book.dto.bank.BankUpdateDTO;
import com.uba.check_book.entity.Bank;
import com.uba.check_book.entity.Country;
import com.uba.check_book.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankMapper {
    public final CountryRepository countryRepository;
    public Bank toEntity(BankCreateDTO dto){
        Bank bank = new Bank();
        bank.setLabel(dto.getLabel());
        bank.setBankId(dto.getBank_id());
        if(dto.getCountryId()!=null){
            Country country = countryRepository.findById(dto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            bank.setCountry(country);
        }
        return  bank;
    }

    public void updateEntity(BankUpdateDTO dto, Bank bank){
        if(dto.getLabel()!=null)bank.setLabel(dto.getLabel());
        if(dto.getBank_id()!=null)bank.setBankId(dto.getBank_id());
        if(dto.getCountryId()!=null){
            Country country = countryRepository.findById(dto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            bank.setCountry(country);
        }
    }

    public BankResponseDTO toDTO(Bank bank){
        BankResponseDTO dto = new BankResponseDTO();
        dto.setId(bank.getId());
        dto.setLabel(bank.getLabel());
        dto.setBank_id(bank.getBankId());
        if(bank.getCountry()!=null){
            dto.setCountryName(bank.getCountry().getLabel());
        }
        return dto;
    }
}
