package com.example.cardatabase4.dto;

import com.example.cardatabase4.entity.AppUser;

public record CreateUserRequest(String username, String password, String role) {
    public AppUser toEntity() { return new AppUser(username, password, role); }
}
