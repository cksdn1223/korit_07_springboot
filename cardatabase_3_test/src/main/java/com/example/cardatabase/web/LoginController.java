package com.example.cardatabase.web;

import com.example.cardatabase.domain.AccountCredentials;
import com.example.cardatabase.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authentication;
    public LoginController(JwtService jwtService, AuthenticationManager authentication) {
        this.jwtService = jwtService;
        this.authentication = authentication;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        // 여기에 토큰 생성하고 응답의 Authorization 헤더로 전송해주는 로직작성
        UsernamePasswordAuthenticationToken creds =
                new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        Authentication auth = authentication.authenticate(creds);

        String jwts = jwtService.getToken(auth.getName());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }
}
