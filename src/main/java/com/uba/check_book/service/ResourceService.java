package com.uba.check_book.service;

import com.uba.check_book.dto.ResourceDTO;

import java.util.List;

public interface ResourceService {
    List<ResourceDTO> findAll();
    ResourceDTO save(ResourceDTO resourceDTO);
    ResourceDTO update(Long id, ResourceDTO resourceDTO);
    ResourceDTO findById(Long id);
    void deleteById(Long id);
}
