package com.example.shoppinglist.controller;

import com.example.shoppinglist.dto.ItemsRequestRecord;
import com.example.shoppinglist.entity.Items;
import com.example.shoppinglist.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemsController {
    private final ItemsService itemsService;
    // create
    @PostMapping("/item")
    public ResponseEntity<Items> createItem(@RequestBody ItemsRequestRecord itemsRequestRecord, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(itemsService.createItem(itemsRequestRecord, userDetails));
    }
    // update
    @PatchMapping("/item/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable Long id, @RequestBody ItemsRequestRecord itemsRequestRecord, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(itemsService.updateItem(id, itemsRequestRecord, userDetails));
    }
    // delete
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemsService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
