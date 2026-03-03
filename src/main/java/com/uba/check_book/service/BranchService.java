package com.uba.check_book.service;

import com.uba.check_book.dto.branch.BranchCreateDTO;
import com.uba.check_book.dto.branch.BranchResponseDTO;
import com.uba.check_book.dto.branch.BranchUpdateDTO;
import com.uba.check_book.entity.Branch;
import com.uba.check_book.entity.Permission;
import com.uba.check_book.mapper.BranchMapper;
import com.uba.check_book.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService {
    public final BranchMapper branchMapper;
    public final BranchRepository branchRepository;

    public BranchResponseDTO createBranch(BranchCreateDTO dto){
        Branch branch = branchMapper.toEntity(dto);
        Branch branchSaved = branchRepository.save(branch);
        return branchMapper.toDTO(branchSaved);
    }

    public BranchResponseDTO updateBranch(Long id, BranchUpdateDTO dto){
        Branch branch = branchRepository.findById(id)
                .orElseThrow(()->new RuntimeException("branch not found"));
        branchMapper.updateEntity(dto,branch);
        Branch branchUpdated = branchRepository.save(branch);

        return branchMapper.toDTO(branchUpdated);
    }

    public Optional<Branch> findById(Long id){
        return branchRepository.findById(id);
    }

    public List<BranchResponseDTO> findAll(){
        return branchRepository.findAll(Sort.by("label"))
                .stream()
                .map(branchMapper::toDTO)
                .toList();
    }

    public void deleteBranch(Long id){
        branchRepository.deleteById(id);
    }
}
