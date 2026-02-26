package com.uba.check_book.controller;

import com.uba.check_book.dto.RoleDTO;
import com.uba.check_book.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('ROLE:READ')")
    public List<RoleDTO> getRoles(){
        return roleService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('ROLE:CREATE')")
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO){
        return roleService.save(roleDTO);
    }

    @PutMapping("/id")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('ROLE:UPDATE')")
    public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO){
        return roleService.save(roleDTO);
    }

    @GetMapping("/id")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('ROLE:READ')")
    public RoleDTO getRoleById(@PathVariable Long id){
        return roleService.findById(id);
    }

    @DeleteMapping("/id")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('ROLE:DELETE')")
    public void deleteRoleById(@PathVariable Long id){
        roleService.deleteById(id);
    }
}
