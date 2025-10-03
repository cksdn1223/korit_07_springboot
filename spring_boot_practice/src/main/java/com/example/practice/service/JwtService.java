package com.example.practice.service;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    static final long EXPIRATION = 86400000; // 24시간
    static final String PREFIX = "Bearer"; // 이 부분은 이제 사용되지 않습니다.
    static final Key key = Jwts.SIG.HS256.key().build();

    // 토큰 생성
    public String getToken(String username) {
        String token = Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
        return token;
    }

    // 토큰에서 사용자 이름 추출
    public String getAuthUser(String token) {
        if (token != null) {
            try {
                String user = Jwts.parser()
                        .verifyWith((SecretKey) key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject();
                return user;
            } catch (Exception e) {
                // 토큰 파싱 중 오류 발생 시 (예: 만료된 토큰)
                return null;
            }
        }
        return null;
    }
}
