package com.uba.check_book.controller;

import com.uba.check_book.dto.ResourceDTO;
import com.uba.check_book.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('RESSOURCE:READ')")
    public List<ResourceDTO> getAllResources(){
        return resourceService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('RESSOURCE:CREATE')")
    public ResourceDTO createResource(@RequestBody ResourceDTO resourceDTO){
        return resourceService.save(resourceDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('RESSOURCE:READ')")
    public ResourceDTO getResourceById(@PathVariable Long id){
        return resourceService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('RESSOURCE:UPDATE')")
    public ResourceDTO updateResource(@PathVariable Long id, @RequestBody ResourceDTO resourceDTO){
        return resourceService.update(id,resourceDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('RESSOURCE:DELETE')")
    public void deleteResource(@PathVariable Long id){
        resourceService.deleteById(id);
    }
}
