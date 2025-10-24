package com.example.shoppinglist;

import com.example.shoppinglist.entity.Users;
import com.example.shoppinglist.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ShoppinglistApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);
	}

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    @Override
    public void run(String... args) throws Exception {
        Users user1 = new Users("user", passwordEncoder.encode("user"));
        usersRepository.save(user1);
    }
}
