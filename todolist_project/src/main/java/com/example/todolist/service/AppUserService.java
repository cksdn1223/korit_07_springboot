package com.example.todolist.service;

import com.example.todolist.dto.AccountCredentialsRecord;
import com.example.todolist.dto.AppUserRecord;
import com.example.todolist.entity.AppUser;
import com.example.todolist.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public List<AppUserRecord> getAllUser() {
        return appUserRepository.findAll().stream()
                .map(AppUserRecord::fromEntity)
                .collect(Collectors.toList());
    }

    public ResponseEntity<AccountCredentialsRecord> saveUser(AccountCredentialsRecord request) {
        Optional<AppUser> searchUser = appUserRepository.findByUsername(request.username());
        if(searchUser.isEmpty()){
            appUserRepository.save(new AppUser(request.username(), passwordEncoder.encode(request.password()),"USER"));
            return new ResponseEntity<>(request, HttpStatus.CREATED);
        }
        else throw new RuntimeException("이미 존재하는 사용자입니다.");
    }
}
