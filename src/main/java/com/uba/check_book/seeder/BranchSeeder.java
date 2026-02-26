package com.uba.check_book.seeder;

import com.uba.check_book.entity.Bank;
import com.uba.check_book.entity.Branch;
import com.uba.check_book.repository.BankRepository;
import com.uba.check_book.repository.BranchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
public class BranchSeeder implements CommandLineRunner {
    private final BranchRepository branchRepository;
    private final BankRepository bankRepository;

    public BranchSeeder(BranchRepository branchRepository,
                        BankRepository bankRepository) {
        this.branchRepository = branchRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (branchRepository.count() > 0) {
            return;
        }

        Bank ubaCongo = bankRepository.findByBankId("CD").orElseThrow();
        Bank ubaIvory_cost = bankRepository.findByBankId("CI").orElseThrow();
        Bank ubaCameroon = bankRepository.findByBankId("CM").orElseThrow();

        List<Branch> branches = List.of(

                createBranch("Liberation", 6801, ubaCongo),
                createBranch("Civil servent", 6802, ubaCongo),
                createBranch("Siege", 6899, ubaCongo),

                createBranch("Plateau", 7001, ubaIvory_cost),
                createBranch("Yopougon", 7001, ubaIvory_cost),
                createBranch("Angré", 7002, ubaCameroon)

        );

        branchRepository.saveAll(branches);

        System.out.println("Branches seeded successfully ✅");
    }

    private Branch createBranch(String label, int solId, Bank bank) {
        Branch branch = new Branch();
        branch.setLabel(label);
        branch.setSolId(solId);
        branch.setBank(bank);
        return branch;
    }
}
