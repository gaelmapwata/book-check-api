package com.uba.check_book.mapper;

import com.uba.check_book.dto.bank.BankResponseDTO;
import com.uba.check_book.dto.branch.BranchCreateDTO;
import com.uba.check_book.dto.branch.BranchResponseDTO;
import com.uba.check_book.dto.branch.BranchUpdateDTO;
import com.uba.check_book.entity.Bank;
import com.uba.check_book.entity.Branch;
import com.uba.check_book.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchMapper {
    public final BankRepository bankRepository;
    public Branch toEntity(BranchCreateDTO dto){
        Branch branch = new Branch();
        branch.setSolId(dto.getSolId());
        branch.setLabel(dto.getLabel());
        if(dto.getBankId()!=null){
            Bank bank = bankRepository.findById(dto.getBankId())
                    .orElseThrow(()->new RuntimeException("bank not found"));
            branch.setBank(bank);
        }
        return branch;
    }

    public void updateEntity(BranchUpdateDTO dto,Branch branch){
      if(dto.getLabel()!=null)branch.setLabel(dto.getLabel());
      if(dto.getSolId()!=null)branch.setSolId(dto.getSolId());
      if(dto.getBankId()!=null){
          Bank bank = bankRepository.findById(dto.getBankId())
                  .orElseThrow(()->new RuntimeException("bank not found"));
          branch.setBank(bank);
      }
    }

    public BranchResponseDTO toDTO(Branch branch){
        BranchResponseDTO dto = new BranchResponseDTO();
        dto.setId(branch.getId());
        dto.setLabel(branch.getLabel());
        dto.setSolId(branch.getSolId());
        if(branch.getBank()!=null){
            dto.setBankName(branch.getBank().getLabel());
        }
        return dto;
    }
}
