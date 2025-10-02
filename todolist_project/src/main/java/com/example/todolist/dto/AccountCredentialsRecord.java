package com.example.todolist.dto;

import com.example.todolist.entity.AppUser;

public record AccountCredentialsRecord(String username, String password) {
    public static AccountCredentialsRecord fromEntity(AppUser appUser) {
        return new AccountCredentialsRecord(
                appUser.getUsername(),
                appUser.getPassword()
        );
    }
}
