package com.uba.check_book.repository;

import com.uba.check_book.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank,Long> {
    Optional<Bank> findByBankId(String bankId);
}
