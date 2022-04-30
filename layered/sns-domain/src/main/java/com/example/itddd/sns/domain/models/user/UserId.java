package com.example.itddd.sns.domain.models.user;

import java.util.Objects;

public record UserId(String value) {
    public UserId {
        Objects.requireNonNull(value);
    }
}
