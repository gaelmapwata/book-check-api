package com.uba.check_book.seeder;

import com.uba.check_book.entity.Permission;
import com.uba.check_book.entity.Resource;
import com.uba.check_book.repository.PermissionRepository;
import com.uba.check_book.repository.ResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(5)
public class PermissionSeeder implements CommandLineRunner {
    private final PermissionRepository permissionRepository;
    private final ResourceRepository resourceRepository;

    public PermissionSeeder(PermissionRepository permissionRepository, ResourceRepository resourceRepository) {
        this.permissionRepository = permissionRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (permissionRepository.count() > 0) {
            return;
        }

        Resource userResource = resourceRepository
                .findByName("user")
                .orElseThrow(() -> new RuntimeException("USER resource not found"));


        List<Permission> permissions = List.of(
                createPermission("USER:CREATE", "create", "peut créer un utilisateur", userResource),
                createPermission("USER:READ", "read", "peut voir la liste des utilisateurs", userResource),
                createPermission("USER:UPDATE", "update", "peut modifier un utilisateur", userResource),
                createPermission("USER:DELETE", "delete", "peut supprimer un utilisateur", userResource)
        );

        permissionRepository.saveAll(permissions);

        System.out.println("Permissions seeded successfully ✅");
    }

    private Permission createPermission(String slug, String name, String description, Resource resource) {
        Permission permission = new Permission();
        permission.setSlug(slug);
        permission.setName(name);
        permission.setDescription(description);
        permission.setResource(resource);
        return permission;
    }

}
