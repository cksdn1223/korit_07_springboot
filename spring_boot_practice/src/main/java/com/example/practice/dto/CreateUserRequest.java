package com.example.practice.dto;

import com.example.practice.entity.AppUser;

public record CreateUserRequest(String username, String password) {
}
