package com.example.practice.controller;

import com.example.practice.dto.AccountCredentials;
import com.example.practice.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        // user 인증
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
                credentials.username(), credentials.password()
        );
        Authentication auth = authenticationManager.authenticate(creds);

        // 토큰 생성
        String jwts = jwtService.getToken(auth.getName());

        // HttpOnly 쿠키 생성
        ResponseCookie cookie = ResponseCookie.from("token", jwts)
                .httpOnly(true)
                .path("/")
                .maxAge(60 * 60) // 1시간
                .build();

        // 생성된 쿠키를 헤더에 담아 응답
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("로그인에 성공했습니다. 이제 주소창에서 직접 API를 호출할 수 있습니다.");
    }
}
