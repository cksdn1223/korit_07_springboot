package com.example.cardatabase4.controller;

import com.example.cardatabase4.entity.Owner;
import com.example.cardatabase4.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OwnerController {
    private final OwnerService ownerService;

    // 1. owner 전체 조회
    @GetMapping("/owners")
    public List<Owner> findAllOwners(){
        return ownerService.findAllOwners();
    }

    // 2. id 별 조회
    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> findOwnerById(@PathVariable Long id) {
        return ownerService.findOwnerById(id)
                .map(owner -> ResponseEntity.ok().body(owner))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. owner 객체 추가
    @PostMapping("/owners")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerService.addOwner(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    // 4. owner 객체 삭제
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable Long id) {
        if (ownerService.deleteOwnerById(id)) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    // 5. owner 객체 수정
    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> updateOwnerById(@PathVariable Long id, @RequestBody Owner ownerDetails) {
        return ownerService.updateOwnerById(id,ownerDetails)
                .map(owner -> ResponseEntity.ok().body(owner))
                .orElse(ResponseEntity.notFound().build());
    }

}
