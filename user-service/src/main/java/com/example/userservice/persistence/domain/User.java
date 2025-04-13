package com.example.userservice.persistence.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50, unique = true)
    String email;

    @Column(nullable = false, unique = true)
    String pwd;

    @Column(nullable = false, length = 50)
    String name;

    @Column(nullable = false, unique = true)
    String userId;

    @Builder
    public User(Long id, String email, String pwd, String name, String userId) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.userId = userId;
    }
}
