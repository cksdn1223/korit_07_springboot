package com.example.cardatabase4.service;

import com.example.cardatabase4.entity.Owner;
import com.example.cardatabase4.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    // 1. 전체 조회
    public List<Owner> findAllOwners(){
        return ownerRepository.findAll();
    }

    // 2. id 별 조회
    public Optional<Owner> findOwnerById(Long id) {
        if (ownerRepository.existsById(id)) return ownerRepository.findById(id);
        else return Optional.empty();
    }

    // 3. owner 객체 추가
    public Owner addOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    // 4. owner 객체 삭제
    public boolean deleteOwnerById(Long id){
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 5. owner 객체 수정
    @Transactional
    public Optional<Owner> updateOwnerById(Long id, Owner ownerDetails){
        return ownerRepository.findById(id)
                .map(owner -> {
                    owner.setLastName(ownerDetails.getLastName());
                    owner.setFirstName(ownerDetails.getFirstName());
                    return owner;
                });
    }
}
