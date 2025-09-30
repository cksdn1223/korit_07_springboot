package com.example.cardatabase4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exists.")
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        // 클라이언트에게 반환할 오류 메시지
        super("사용자 이름 '" + username + "'이(가) 이미 존재합니다.");
    }
}