package com.shoppinglist.shoppinglist2.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.net.http.HttpRequest;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATION_TIME = 86400000;
    static final String PREFIX = "Bearer ";

    private final SecretKey key = Jwts.SIG.HS256.key().build();

    // JWT 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // 요청 헤더에서 JWT를 파싱하여 사용자 이름(subject) 추출
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token != null && token.startsWith(PREFIX)) {
            try {
                String user = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token.replace(PREFIX,""))
                        .getPayload()
                        .getSubject();

                if(user != null) return user;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
