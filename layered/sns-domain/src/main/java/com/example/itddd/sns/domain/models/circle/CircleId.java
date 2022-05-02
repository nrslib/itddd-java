package com.example.itddd.sns.domain.models.circle;

import java.util.Objects;

public record CircleId(String value) {
    public CircleId {
        Objects.requireNonNull(value);
    }
}
