package com.example.practice.dto;

import com.example.practice.entity.AppUser;

public record AppUserResponse(Long id, String username, String role) {
    public static AppUserResponse fromEntity(AppUser appUser) {
        return new AppUserResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getRole()
        );
    }
}