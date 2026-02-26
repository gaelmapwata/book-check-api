package com.uba.check_book.seeder;

import com.uba.check_book.entity.Permission;
import com.uba.check_book.entity.Role;
import com.uba.check_book.repository.PermissionRepository;
import com.uba.check_book.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@Order(6)
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleSeeder(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() > 0) {
            return;
        }


        Role controller = new Role();
        controller.setName("CONTROLLER");

        Role admin = new Role();
        admin.setName("ADMIN");

        Role teller = new Role();
        teller.setName("TELLER");

        roleRepository.saveAll(List.of(controller, admin, teller));

        List<Permission> allPermissions = permissionRepository.findAll();

        admin.setPermissions(new HashSet<>(allPermissions));
        roleRepository.save(admin);

        List<Permission> tellerPerms = allPermissions.stream()
                .filter(p -> p.getSlug().startsWith("USER:"))
                .toList();
        teller.setPermissions(new HashSet<>(tellerPerms));
        roleRepository.save(teller);

        System.out.println("Roles seeded successfully ✅");
    }
}