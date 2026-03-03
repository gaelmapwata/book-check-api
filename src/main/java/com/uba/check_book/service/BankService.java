package com.uba.check_book.service;

import com.uba.check_book.dto.bank.BankCreateDTO;
import com.uba.check_book.dto.bank.BankResponseDTO;
import com.uba.check_book.dto.bank.BankUpdateDTO;
import com.uba.check_book.entity.Bank;
import com.uba.check_book.mapper.BankMapper;
import com.uba.check_book.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {
    public final BankMapper bankMapper;
    public final BankRepository bankRepository;

    public BankResponseDTO createBank(BankCreateDTO dto){
        Bank bank = bankMapper.toEntity(dto);
        Bank bankSaved = bankRepository.save(bank);

        return bankMapper.toDTO(bankSaved);
    }

    public BankResponseDTO updateBank(Long id, BankUpdateDTO dto){
        Bank bank = bankRepository.findById(id)
                .orElseThrow(()->new RuntimeException("bank not found"));
        bankMapper.updateEntity(dto,bank);

        Bank bankUpdated = bankRepository.save(bank);
        return bankMapper.toDTO(bankUpdated);
    }

    public Optional<Bank> findById(Long id){
        return bankRepository.findById(id);
    }

    public List<BankResponseDTO>findAll(){
        return bankRepository.findAll(Sort.by("label"))
                .stream()
                .map(bankMapper::toDTO)
                .toList();
    }

    public void deleteBank(Long id){
        bankRepository.deleteById(id);
    }
}
