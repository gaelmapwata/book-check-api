package com.uba.check_book.service;

import com.uba.check_book.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();
    RoleDTO save(RoleDTO roleDTO);
    RoleDTO update(Long id,RoleDTO roleDTO);
    RoleDTO findById(Long id);
    void deleteById(Long id);

}
