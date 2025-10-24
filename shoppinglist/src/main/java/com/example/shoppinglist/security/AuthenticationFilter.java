package com.example.shoppinglist.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * 모든 HTTP 요청에 대해 한 번씩 실행되는 필터입니다.
 * 요청 헤더의 JWT 토큰을 검증하고, 유효한 경우 Spring Security 컨텍스트에 인증 정보를 설정합니다.
 * OncePerRequestFilter를 상속하여 어떤 서블릿 컨테이너에서든 요청당 한 번의 실행을 보장합니다.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 로그인 API 요청은 토큰 검증 없이 통과시킵니다.
        if (request.getServletPath().equals("/login") && request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())) {
            filterChain.doFilter(request, response);
            return;
        }
        // 요청 헤더에서 Authorization 값을 가져옵니다. (JWT 토큰)
        String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (jws != null) {
            // JWT 토큰에서 사용자 이름을 추출합니다.
            String user = jwtService.getAuthUser(request);
            if (user != null) {
                // 사용자 이름으로 DB에서 전체 사용자 정보를 조회합니다. (권한 포함)
                UserDetails userDetails = userDetailsService.loadUserByUsername(user);
                // 인증 객체를 생성하여 SecurityContext에 저장합니다.
                // 이 시점부터 해당 요청은 인증된 사용자의 요청으로 간주됩니다.
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // 다음 필터로 요청과 응답을 전달합니다.
        filterChain.doFilter(request, response);
    }
}