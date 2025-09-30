package com.example.cardatabase4.service;

import com.example.cardatabase4.entity.AppUser;
import com.example.cardatabase4.exception.UserAlreadyExistsException;
import com.example.cardatabase4.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public List<AppUser> getAllUser() {
        return appUserRepository.findAll();
    }

    public AppUser findByUsername(String username){
        return appUserRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username+" 을 찾을 수 없습니다."));
    }
    public AppUser saveUser(AppUser appUser) {
        if(appUserRepository.findByUsername(appUser.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(appUser.getUsername() + "은 이미 존재하는 이름입니다.");
        }
        AppUser newUser = new AppUser(appUser.getUsername(),passwordEncoder.encode(appUser.getPassword()),appUser.getRole());
        return appUserRepository.save(newUser);
    }
}
