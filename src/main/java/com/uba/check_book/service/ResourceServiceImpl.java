package com.uba.check_book.service;

import com.uba.check_book.dto.ResourceDTO;
import com.uba.check_book.entity.Resource;
import com.uba.check_book.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements  ResourceService{
    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public List<ResourceDTO>findAll(){
      return resourceRepository.findAll().stream()
              .map(r-> new ResourceDTO(
                      r.getId(),
                      r.getName()
              ))
              .collect(Collectors.toList());
    }

    @Override
    public ResourceDTO save(ResourceDTO resourceDTO){
        Resource resource = new Resource();
        resource.setName(resourceDTO.getName());

        Resource resourceSaved = resourceRepository.save(resource);
        return new ResourceDTO(
                resourceSaved.getId(),
                resourceSaved.getName()
        );
    }
    @Override
    public ResourceDTO update(Long id, ResourceDTO resourceDTO){
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(()->new RuntimeException("resource not found"));

        resource.setName(resourceDTO.getName());
        Resource resourceUpdated = resourceRepository.save(resource);
        return new ResourceDTO(
                resourceUpdated.getId(),
                resourceUpdated.getName()
        );
    }
    @Override
    public ResourceDTO findById(Long id){
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(()->new RuntimeException("resource not found"));
        return new ResourceDTO(
                resource.getId(),
                resource.getName()
        );
    }

    @Override
    public void deleteById(Long id){
        resourceRepository.deleteById(id);
    }

}
