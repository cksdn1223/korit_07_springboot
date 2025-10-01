package com.example.practice.controller;

import com.example.practice.dto.AppUserResponse;
import com.example.practice.dto.CreateUserRequest;
import com.example.practice.service.AppUserService;
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
        return appUserService.getAllUser();
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUserResponse> findUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AppUserResponse> saveUser(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(appUserService.saveUser(request), HttpStatus.CREATED);
    }

}
