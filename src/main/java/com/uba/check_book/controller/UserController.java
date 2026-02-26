package com.uba.check_book.controller;

import com.uba.check_book.dto.user.UserCreateDTO;
import com.uba.check_book.dto.user.UserResponseDTO;
import com.uba.check_book.dto.user.UserUpdateDTO;
import com.uba.check_book.entity.User;
import com.uba.check_book.security.UserPrincipal;
import com.uba.check_book.service.UserService;
import com.uba.check_book.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:READ')")
    public ResponseEntity<List<UserResponseDTO>> index(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<UserResponseDTO> response = userService.findAll(page, limit);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:CREATE')")
    public ResponseEntity<UserResponseDTO> store(
            @Validated @RequestBody UserCreateDTO dto,
            @AuthenticationPrincipal UserPrincipal currentUser
    ) {
        Long currentUserId = currentUser.getId();
        UserResponseDTO savedUser = userService.createUser(dto, currentUserId);
        return ResponseEntity.status(201).body(savedUser);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:READ')")
    public ResponseEntity<UserResponseDTO> show(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return ResponseEntity.ok(userMapper.toDTO(user));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:UPDATE')")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id,
            @Validated @RequestBody UserUpdateDTO dto
    ) {
        UserResponseDTO updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/lock")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:LOCK')")
    public ResponseEntity<UserResponseDTO> lockUser(@PathVariable Long id) {
        UserResponseDTO locked = userService.lockUser(id);
        return ResponseEntity.ok(locked);
    }
    @PostMapping("/{id}/unlock")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:UNLOCK')")
    public ResponseEntity<UserResponseDTO> unlockUser(@PathVariable Long id) {
        UserResponseDTO unlocked = userService.unlockUser(id);
        return ResponseEntity.ok(unlocked);
    }

    @PostMapping("/{id}/validate")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('USER:VALIDATE')")
    public ResponseEntity<UserResponseDTO> validateUser(
            @PathVariable Long id,
            @RequestAttribute("userId") Long currentUserId
    ) {
        UserResponseDTO validated = userService.validateUser(id, currentUserId);
        return ResponseEntity.ok(validated);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserResponseDTO> addRoles(
            @PathVariable Long id,
            @RequestBody List<Long> roleIds
    ) {
        UserResponseDTO user = userService.addRoles(id, roleIds);
        return ResponseEntity.ok(user);
    }
}