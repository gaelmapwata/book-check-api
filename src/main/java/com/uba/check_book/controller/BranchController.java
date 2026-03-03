package com.uba.check_book.controller;

import com.uba.check_book.dto.branch.BranchCreateDTO;
import com.uba.check_book.dto.branch.BranchResponseDTO;
import com.uba.check_book.dto.branch.BranchUpdateDTO;
import com.uba.check_book.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {
    public final BranchService branchService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BRANCH:READ')")
    public ResponseEntity<List<BranchResponseDTO>>getAllBranch(){
        List<BranchResponseDTO>response= branchService.findAll();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BRANCH:CREATE')")
    public ResponseEntity<BranchResponseDTO>saveBranch(@RequestBody BranchCreateDTO dto){
        BranchResponseDTO branchSaved = branchService.createBranch(dto);
        return ResponseEntity.status(201).body(branchSaved);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BRANCH:UPDATE')")
    public ResponseEntity<BranchResponseDTO>updateBranch(
            @PathVariable Long id,
            @RequestBody BranchUpdateDTO dto
            ){
        BranchResponseDTO updatedBranch = branchService.updateBranch(id,dto);
        return ResponseEntity.status(201).body(updatedBranch);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('BRANCH:DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
