package com.shoppinglist.shoppinglist2;

import com.shoppinglist.shoppinglist2.domain.ShoppingItem;
import com.shoppinglist.shoppinglist2.domain.ShoppingItemRepository;
import com.shoppinglist.shoppinglist2.domain.User;
import com.shoppinglist.shoppinglist2.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class Shoppinglist2Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Shoppinglist2Application.class, args);
	}

    private final UserRepository userRepository;
    private final ShoppingItemRepository shoppingItemRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("user", passwordEncoder.encode("user"), "USER");
        User user2 = new User("admin", passwordEncoder.encode("admin"), "ADMIN");
        userRepository.save(user1);
        userRepository.save(user2);
        shoppingItemRepository.save(new ShoppingItem("우유", "1개", false, user1));
        shoppingItemRepository.save(new ShoppingItem("빵", "1봉지", true, user1));
        shoppingItemRepository.save(new ShoppingItem("사과", "3개", false, user2));
    }
}
