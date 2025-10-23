package com.example.cardatabase.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false, updatable = false)
    private Long id;

    @Column(nullable=false,unique=true)
    private final String username;

    @Column(nullable = false)
    private final String password;

    @Column(nullable = false)
    private final String role;
}
