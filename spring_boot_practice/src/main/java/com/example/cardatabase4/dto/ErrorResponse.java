package com.example.cardatabase4.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int status, String message) {}