package com.example.itddd.web.utility.domain.user;

import com.example.itddd.sns.domain.models.user.*;

import java.util.UUID;

public class InMemoryUserFactory implements UserFactory {
    @Override
    public User create(UserName name) {
        var rawId = UUID.randomUUID().toString();
        var id = new UserId(rawId);

        return new User(id, name, UserType.Normal);
    }
}
