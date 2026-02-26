package com.uba.check_book.seeder;

import com.uba.check_book.entity.Bank;
import com.uba.check_book.entity.Country;
import com.uba.check_book.repository.BankRepository;
import com.uba.check_book.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class BankSeeder implements CommandLineRunner {
    private final BankRepository bankRepository;
    private final CountryRepository countryRepository;

    public BankSeeder(BankRepository bankRepository,
                      CountryRepository countryRepository) {
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (bankRepository.count() > 0) {
            return;
        }

        // Congo
        Country congo =countryRepository.findByCode2("CD").orElseThrow(() -> new RuntimeException("Country CD not found"));
        // Nigeria
        Country ivory_cost =  countryRepository.findByCode2("CI").orElseThrow(() -> new RuntimeException("Country CI not found"));
        // Kenya
        Country cameroon = countryRepository.findByCode2("CM").orElseThrow(() -> new RuntimeException("Country CM not found"));

        List<Bank> banks = List.of(

                createBank("Congo DR", "CD", congo),

                createBank("Ivory Cost", "CI", ivory_cost),
                createBank("Cameroon", "CM", cameroon)

        );

        bankRepository.saveAll(banks);

        System.out.println("Banks seeded successfully ✅");
    }

    private Bank createBank(String label, String bankId, Country country) {
        Bank bank = new Bank();
        bank.setLabel(label);
        bank.setBankId(bankId);
        bank.setCountry(country);
        return bank;
    }
}
