package com.example.shoppinglist.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때 호출되는 핸들러입니다.
 * 기본 동작(로그인 페이지로 리다이렉트) 대신,
 * 401 Unauthorized 상태 코드와 함께 커스텀 JSON 에러 메시지를 응답합니다.
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    /**
     * 인증 예외가 발생했을 때 실행되는 메소드입니다.
     * SecurityConfig의 exceptionHandling 설정에 의해 등록됩니다.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.println("Error : " + authException.getMessage());
    }
}
