package com.example.cardatabase4.controller;

import com.example.cardatabase4.dto.AppUserResponse;
import com.example.cardatabase4.dto.CreateUserRequest;
import com.example.cardatabase4.entity.AppUser;
import com.example.cardatabase4.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    @GetMapping("/all")
    public List<AppUserResponse> getAllUsers() {
        return appUserService.getAllUser().stream()
                .map(AppUserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUserResponse> findUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(AppUserResponse.fromEntity(appUserService.findByUsername(username)),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AppUserResponse> saveUser(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(AppUserResponse.fromEntity(appUserService.saveUser(request.toEntity())),HttpStatus.CREATED);
    }

}
