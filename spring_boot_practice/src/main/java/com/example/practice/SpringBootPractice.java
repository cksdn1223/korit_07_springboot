package com.example.practice;

import com.example.practice.entity.AppUser;
import com.example.practice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringBootPractice implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPractice.class, args);
	}

	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		appUserRepository.save(new AppUser("user", passwordEncoder.encode("user"),"USER"));
		appUserRepository.save(new AppUser("admin",passwordEncoder.encode("admin"),"admin"));
	}
}

