package com.example.shoppinglist.controller;

import com.example.shoppinglist.entity.Items;
import com.example.shoppinglist.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    // 로그인한 유저의 모든 아이템 불러오기
    @GetMapping("/api/items")
    public ResponseEntity<List<Items>> getItems(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(usersService.getItems(userDetails));
    }
}
