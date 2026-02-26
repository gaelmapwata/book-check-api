package com.uba.check_book.seeder;

import com.uba.check_book.entity.Branch;
import com.uba.check_book.entity.Role;
import com.uba.check_book.entity.User;
import com.uba.check_book.repository.BranchRepository;
import com.uba.check_book.repository.RoleRepository;
import com.uba.check_book.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@Order(7)
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final RoleRepository roleRepository;

    public UserSeeder(UserRepository userRepository, BranchRepository branchRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        Branch branch = branchRepository.findById(1L).orElseThrow();

        Role adminRole = roleRepository.findById(2L).orElseThrow();
        Role tellerRole = roleRepository.findById(3L).orElseThrow();

        User adminUser = new User();
        adminUser.setEmail("admin@uba.com");
        adminUser.setBranch(branch);
        adminUser.setLocked(false);
        adminUser.setTotalLoginAttempt(0);
        adminUser.setDeleteAt(null);
        adminUser.setRoles(Set.of(adminRole));

        User tellerUser = new User();
        tellerUser.setEmail("teller@uba.com");
        tellerUser.setBranch(branch);
        tellerUser.setLocked(false);
        tellerUser.setTotalLoginAttempt(0);
        tellerUser.setDeleteAt(null);
        tellerUser.setRoles(Set.of(tellerRole));

        userRepository.saveAll(List.of(adminUser, tellerUser));

        System.out.println("Users seeded successfully ✅");
    }
}