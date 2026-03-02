package com.uba.check_book.controller;

import com.uba.check_book.dto.permission.PermissionCreateDTO;
import com.uba.check_book.dto.permission.PermissionResponseDTO;
import com.uba.check_book.dto.permission.PermissionUpdateDTO;
import com.uba.check_book.dto.user.UserCreateDTO;
import com.uba.check_book.dto.user.UserResponseDTO;
import com.uba.check_book.dto.user.UserUpdateDTO;
import com.uba.check_book.entity.Permission;
import com.uba.check_book.entity.User;
import com.uba.check_book.mapper.PermissionMapper;
import com.uba.check_book.security.UserPrincipal;
import com.uba.check_book.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;
    private final PermissionMapper permissionMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('PERMISSION:READ')")
    public ResponseEntity<List<PermissionResponseDTO>> index(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<PermissionResponseDTO> response = permissionService.findAll(page, limit);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('PERMISSION:CREATE')")
    public ResponseEntity<PermissionResponseDTO> store(
            @Validated @RequestBody PermissionCreateDTO dto
    ) {
        PermissionResponseDTO savedPermission = permissionService.createPermission(dto);
        return ResponseEntity.status(201).body(savedPermission);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('PERMISSION:READ')")
    public ResponseEntity<PermissionResponseDTO> show(@PathVariable Long id) {
        Permission permission = permissionService.findById(id)
                .orElseThrow(() -> new RuntimeException("permission not found with id " + id));
        return ResponseEntity.ok(permissionMapper.toDTO(permission));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('PERMISSION:UPDATE')")
    public ResponseEntity<PermissionResponseDTO> update(
            @PathVariable Long id,
            @Validated @RequestBody PermissionUpdateDTO dto
    ) {
        PermissionResponseDTO updated = permissionService.updatePermission(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('PERMISSION:DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

}
