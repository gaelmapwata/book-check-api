package com.uba.check_book.service;

import com.uba.check_book.dto.RoleDTO;
import com.uba.check_book.entity.Role;
import com.uba.check_book.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements  RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO>findAll(){
        return roleRepository.findAll()
                .stream()
                .map(r-> new RoleDTO(
                        r.getId(),
                        r.getName()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public RoleDTO save(RoleDTO roleDTO){
        Role role = new Role();
        role.setName(roleDTO.getName());
        Role roleSaved = roleRepository.save(role);

        return new RoleDTO(
                roleSaved.getId(),
                roleSaved.getName()
        );
    }

    @Override
    public RoleDTO update(Long id,RoleDTO roleDTO){
        Role role = roleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Role not found"));
        role.setName(roleDTO.getName());
        Role roleUpdated = roleRepository.save(role);

        return new RoleDTO(
              roleUpdated.getId(),
              roleUpdated.getName()
        );
    }
    @Override
    public RoleDTO findById(Long id){
        Role role = roleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("role not found"));
        return new RoleDTO(
                role.getId(),
                role.getName()
        );
    }
    @Override
    public void deleteById(Long id){
         roleRepository.deleteById(id);
    }
}
