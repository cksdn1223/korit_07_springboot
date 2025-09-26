package com.example.cardatabase;

import com.example.cardatabase.domain.AppUser;
import com.example.cardatabase.domain.AppUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName(("사용자 이름으로 조회 메서드"))
    void findByUserName() {
        // give
        appUserRepository.save(new AppUser("user1","user1","USER"));
        // when
        Optional<AppUser> appUser = appUserRepository.findByUsername("user1");
        // then
        assertThat(appUser).isPresent();
        assertThat(appUser.get().getRole()).isEqualTo("USER");
    }
}
