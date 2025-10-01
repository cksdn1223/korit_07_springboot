package com.example.practice.service;

import com.example.practice.dto.AppUserResponse;
import com.example.practice.dto.CreateUserRequest;
import com.example.practice.entity.AppUser;
import com.example.practice.exception.UserAlreadyExistsException;
import com.example.practice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public List<AppUserResponse> getAllUser() {
        return appUserRepository.findAll().stream()
                .map(AppUserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public AppUserResponse findByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " 을 찾을 수 없습니다."));
        return AppUserResponse.fromEntity(user);
    }

    public AppUserResponse saveUser(CreateUserRequest request) {
        if (appUserRepository.findByUsername(request.username()).isPresent()) {
            throw new UserAlreadyExistsException(request.username() + "은 이미 존재하는 이름입니다.");
        }
        AppUser newUser = new AppUser(
                request.username(),
                passwordEncoder.encode(request.password()),
                "USER");
        return AppUserResponse.fromEntity(appUserRepository.save(newUser));
    }
}
