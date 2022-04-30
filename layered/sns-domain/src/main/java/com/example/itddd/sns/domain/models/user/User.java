package com.example.itddd.sns.domain.models.user;

import lombok.Getter;

import java.util.Objects;

public class User {
    @Getter
    private final UserId id;
    @Getter
    private UserName name;

    public User(UserId id, UserName name) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);

        this.id = id;
        this.name = name;
    }

    public void changeName(UserName name) {
        this.name = name;
    }
}
