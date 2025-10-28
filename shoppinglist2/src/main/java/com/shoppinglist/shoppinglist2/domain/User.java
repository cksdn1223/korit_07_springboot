package com.shoppinglist.shoppinglist2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

//    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String role;

    // 한 명의 유저는 여러 개의 쇼핑 아이템을 가질 겁니다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ShoppingItem> items;

    // CommandLineRunner 상에서 사용할 간단한 생성자 정의
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
