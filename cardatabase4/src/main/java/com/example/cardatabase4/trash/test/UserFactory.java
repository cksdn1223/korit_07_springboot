package com.example.cardatabase4.trash.test;

import lombok.Builder;
import lombok.ToString;

// TODO: Lombok의 @Builder와 @ToString 애너테이션을 추가하시오.
@Builder
@ToString
class User {
    private String username;
    private String email;
    private int age;
}

public class UserFactory {
    public static void main(String[] args) {
        // TODO: 빌더 패턴을 사용하여 username="user1", email="user1@example.com", age=25 인 User 객체를 생성하고 출력하시오.
        User user1 = User.builder().username("user1").email("user1@example.com").age(25).build();
        System.out.println(user1);
        // TODO: 빌더 패턴을 사용하여 username="user2", email="user2@example.com" 인 User 객체를 생성하고 출력하시오.
        User user2 = User.builder().username("user2").email("user2@example.com").build();
        System.out.println(user2);
    }
}