package com.example.itddd.sns.domain.models.user;

import lombok.Getter;

import java.util.Objects;

@Getter
public class User {
    private final UserId id;
    private UserName name;
    private UserType type;

    public User(UserId id, UserName name, UserType type) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);

        this.id = id;
        this.name = name;
        this.type = type;
    }

    public boolean isPremium() {
        return type == UserType.Premium;
    }

    public void changeName(UserName name) {
        this.name = name;
    }

    public void upgrade() {
        type = UserType.Premium;
    }

    public void downgrade() {
        type = UserType.Normal;
    }
}
