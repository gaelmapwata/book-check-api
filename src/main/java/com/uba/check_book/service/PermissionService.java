package com.uba.check_book.service;

import com.uba.check_book.dto.permission.PermissionCreateDTO;
import com.uba.check_book.dto.permission.PermissionResponseDTO;
import com.uba.check_book.dto.permission.PermissionUpdateDTO;
import com.uba.check_book.entity.Permission;
import com.uba.check_book.entity.User;
import com.uba.check_book.mapper.PermissionMapper;
import com.uba.check_book.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {
    public  final PermissionMapper permissionMapper;
    public final PermissionRepository permissionRepository;
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

    public PermissionResponseDTO createPermission(PermissionCreateDTO dto){
        Permission permission = permissionMapper.toEntity(dto);
        Permission permissionSaved = permissionRepository.save(permission);

        return permissionMapper.toDTO(permissionSaved);
    }

    public PermissionResponseDTO updatePermission(Long id, PermissionUpdateDTO dto){
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("permission not found"));
        permissionMapper.updateEntity(dto,permission);

        Permission permissionUpdated = permissionRepository.save(permission);
        return permissionMapper.toDTO(permissionUpdated);

    }

    public Optional<Permission>findById(Long id){
        return permissionRepository.findById(id);
    }

    public List<PermissionResponseDTO>findAll(int page, int limit){
        if (limit == -1) {
            return permissionRepository.findAll(Sort.by("slug"))
                    .stream()
                    .map(permissionMapper::toDTO)
                    .toList();
        }

        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by("slug"));
        return permissionRepository.findAll(pageRequest)
                .stream()
                .map(permissionMapper::toDTO)
                .toList();
    }

    public void deletePermission(Long id){
        permissionRepository.deleteById(id);
    }
}
