package com.example.cardatabase4.controller;

import com.example.cardatabase4.entity.AppUser;
import com.example.cardatabase4.repository.AppUserRepository;
import com.example.cardatabase4.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/all")
    public List<AppUser> getAllUsers() {
        return appUserService.getAllUser();
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> findUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(appUserService.findByUsername(username),HttpStatus.FOUND);
    }
    @PostMapping("/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) {
        return new ResponseEntity<>(appUserService.saveUser(appUser),HttpStatus.CREATED);
    }

}
