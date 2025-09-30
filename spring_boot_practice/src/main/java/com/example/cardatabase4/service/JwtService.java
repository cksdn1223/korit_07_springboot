package com.example.cardatabase4.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    static final long EXPIRATION = 86400000;
    static final String PREFIX = "Bearer";
    static final Key key = Jwts.SIG.HS256.key().build();

    public String getToken(String username) {
        String token = Jwts.builder().subject(username).expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
        return token;
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token != null) {
            String user = Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build().parseSignedClaims(token.replace(PREFIX, "").trim()).getPayload()
                    .getSubject();
            if(user != null) return user;
        }
        return null;
    }
}
