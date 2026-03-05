package com.uba.check_book.mapper;

import com.uba.check_book.dto.user.UserCreateDTO;
import com.uba.check_book.dto.user.UserResponseDTO;
import com.uba.check_book.dto.user.UserUpdateDTO;
import com.uba.check_book.entity.Branch;
import com.uba.check_book.entity.Role;
import com.uba.check_book.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity(UserCreateDTO dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            user.setBranch(branch);
        }
        return user;
    }
    public void updateEntity(UserUpdateDTO dto, User user) {
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            user.setBranch(branch);
        }
    }
    public UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setLocked(user.isLocked());
        dto.set_validated(user.getIsValidated());
        dto.setTotalLoginAttempt(user.getTotalLoginAttempt());
        if (user.getBranch() != null) {
            dto.setBranchName(user.getBranch().getLabel());
        }
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        }
        return dto;
    }
}
