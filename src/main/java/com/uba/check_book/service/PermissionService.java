package com.uba.check_book.service;

import com.uba.check_book.entity.User;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    /**
     * Vérifie si l'utilisateur a au moins un rôle admin
     */
    public boolean hasRoleAdmin(User user) {
        return user.getRoles().stream()
                .anyMatch(role -> "ADMIN".equalsIgnoreCase(role.getName()));
    }

    /**
     * Vérifie si l'utilisateur possède la permission demandée
     */
    public boolean hasPermission(User user, String permissionSlug) {
        return user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .anyMatch(permission -> permission.getSlug().equalsIgnoreCase(permissionSlug));
    }
}
