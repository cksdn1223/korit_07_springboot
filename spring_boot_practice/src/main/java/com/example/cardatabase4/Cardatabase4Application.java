package com.example.cardatabase4;

import com.example.cardatabase4.entity.AppUser;
import com.example.cardatabase4.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class Cardatabase4Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Cardatabase4Application.class, args);
	}
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		appUserRepository.save(new AppUser("admin",passwordEncoder.encode("admin"),"ADMNIN"));
	}

}

