package com.uba.check_book.service;

import com.uba.check_book.dto.user.UserCreateDTO;
import com.uba.check_book.dto.user.UserResponseDTO;
import com.uba.check_book.dto.user.UserUpdateDTO;
import com.uba.check_book.entity.Permission;
import com.uba.check_book.entity.Role;
import com.uba.check_book.entity.User;
import com.uba.check_book.mapper.UserMapper;
import com.uba.check_book.repository.PermissionRepository;
import com.uba.check_book.repository.RoleRepository;
import com.uba.check_book.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

@Transactional
    public UserResponseDTO createUser(UserCreateDTO dto, Long creatorUserId) {
        User user = userMapper.toEntity(dto);

        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User creator = new User();
        creator.setId(creatorUserId);
        user.setCreatedBy(creator);
        user.setValidatedAskBy(creator);

        User savedUser = userRepository.save(user);

        if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
            List<Role> roles = roleRepository.findAllById(dto.getRoleIds());
            savedUser.setRoles( new HashSet<>(roles));
            savedUser = userRepository.save(savedUser);
        }

        return userMapper.toDTO(savedUser);
    }

@Transactional
    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntity(dto, user);

        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
            List<Role> roles = roleRepository.findAllById(dto.getRoleIds());
            user.setRoles(new HashSet<>(roles));
        }

        User saved = userRepository.save(user);
        return userMapper.toDTO(saved);
    }

    @Transactional
    public UserResponseDTO lockUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLocked(true);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO unlockUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLocked(false);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO validateUser(Long id, Long validatorUserId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User validator = new User();
        validator.setId(validatorUserId);
        user.setValidatedBy(validator);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO addRoles(Long id, List<Long> roleIds) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Role> roles = roleRepository.findAllById(roleIds);
        user.getRoles().addAll(roles);
        return userMapper.toDTO(userRepository.save(user));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll(Sort.by("email"))
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public Permission addRolesToPermission(Long permissionId, List<Long> roleIds) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        List<Role> roles = roleRepository.findAllById(roleIds);
        permission.getRoles().addAll(roles);

        return permissionRepository.save(permission);
    }
}