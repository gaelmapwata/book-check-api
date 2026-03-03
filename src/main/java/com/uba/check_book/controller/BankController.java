package com.uba.check_book.controller;

import com.uba.check_book.dto.bank.BankCreateDTO;
import com.uba.check_book.dto.bank.BankResponseDTO;
import com.uba.check_book.dto.bank.BankUpdateDTO;
import com.uba.check_book.entity.Bank;
import com.uba.check_book.mapper.BankMapper;
import com.uba.check_book.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    public final BankService bankService;
    public  final BankMapper bankMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BANK:READ')")
    public ResponseEntity<List<BankResponseDTO>> getAllBanks(){
        List<BankResponseDTO> response = bankService.findAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BANK:READ')")
    public ResponseEntity<BankResponseDTO> show(@PathVariable Long id) {
        Bank bank = bankService.findById(id)
                .orElseThrow(() -> new RuntimeException("permission not found with id " + id));
        return ResponseEntity.ok(bankMapper.toDTO(bank));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BANK:CREATE')")
    public ResponseEntity<BankResponseDTO> store(
              @RequestBody BankCreateDTO dto){
        BankResponseDTO savedBank = bankService.createBank(dto);
        return ResponseEntity.status(201).body(savedBank);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BANK:UPDATE')")
    public ResponseEntity<BankResponseDTO> update(
            @PathVariable Long id,
            @RequestBody BankUpdateDTO dto
            ){
        BankResponseDTO updatedBank = bankService.updateBank(id,dto);
        return ResponseEntity.status(201).body(updatedBank);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BANK:DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }

}
