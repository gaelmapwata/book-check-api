package com.uba.check_book.controller;

import com.uba.check_book.dto.user.UserResponseDTO;
import com.uba.check_book.entity.Permission;
import com.uba.check_book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("/users/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:UPDATE')")
    public  ResponseEntity<?> addRolesToUser(
            @PathVariable Long userId,
            @RequestBody Map<String, List<Long>> body
    ){
        List<Long> roleIds = body.get("roleIds");
        UserResponseDTO updatedUser = userService.addRoles(userId, roleIds);
        return ResponseEntity.ok(updatedUser);
    }


    @PostMapping("/permissions/{permissionId}/roles")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('PERMISSION:UPDATE')")
    public ResponseEntity<?> addRolesToPermission(
            @PathVariable Long permissionId,
            @RequestBody Map<String, List<Long>> body
    ) {
        List<Long> roleIds = body.get("roleIds");
        Permission permission = userService.addRolesToPermission(permissionId, roleIds);
        return ResponseEntity.ok(permission);
    }
}
