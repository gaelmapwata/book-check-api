package com.uba.check_book.mapper;

import com.uba.check_book.dto.permission.PermissionCreateDTO;
import com.uba.check_book.dto.permission.PermissionResponseDTO;
import com.uba.check_book.dto.permission.PermissionUpdateDTO;
import com.uba.check_book.entity.Permission;
import com.uba.check_book.entity.Resource;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public Permission toEntity(PermissionCreateDTO dto){
        Permission permission = new Permission();
        permission.setName(dto.getName());
        permission.setSlug(dto.getSlug());
        permission.setDescription(dto.getDescription());
        if(dto.getResourceId()!=null){
            Resource resource = new Resource();
            resource.setId(dto.getResourceId());
            permission.setResource(resource);
        }
        return permission;
    }
    public void updateEntity(PermissionUpdateDTO dto,Permission permission){
        if(dto.getName()!=null)permission.setName(dto.getName());
        if(dto.getSlug()!=null)permission.setSlug(dto.getSlug());
        if(dto.getDescription()!=null)permission.setDescription(dto.getDescription());
        if(dto.getResourceId()!=null){
            Resource resource = new Resource();
            resource.setId(dto.getResourceId());
            permission.setResource(resource);
        }
    }
    public PermissionResponseDTO toDTO(Permission permission){
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
        permissionResponseDTO.setId(permission.getId());
        permissionResponseDTO.setName(permission.getName());
        permissionResponseDTO.setSlug(permission.getSlug());
        permissionResponseDTO.setDescription(permission.getDescription());
        if(permission.getResource()!=null){
            permissionResponseDTO.setResourceName(permission.getResource().getName());
        }
        return permissionResponseDTO;
    }
}
