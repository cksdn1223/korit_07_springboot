package com.example.cardatabase;

import com.example.cardatabase.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationFilter authenticationFilter;
    private final AuthEntryPoint exceptionHandler;

    // 패스워드 비교
    public void configGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // 패스워드 암호화 빈 추가
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 인증 빈 추가
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    // cors 관련 빈 추가
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));

        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**",config);
        return source;
    }

    // 필터 체인 빈 추가
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 개발 중 로그인 포함 모든 HTTP 메서드 요청 허용
//        http.csrf(csrf -> csrf.disable())
//                .cors(withDefaults())
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

//        로그인 엔드 포인트 Post 요청 제외 나머지 인증 필요
        http.csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(exceptionHandler))
                ;
        return http.build();
    }
}
