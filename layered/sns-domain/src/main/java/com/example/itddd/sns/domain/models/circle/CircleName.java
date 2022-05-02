package com.example.itddd.sns.domain.models.circle;

import java.util.Objects;

public record CircleName(String value) {
    public CircleName {
        Objects.requireNonNull(value);
        if (value.length() < 3) throw new IllegalArgumentException("サークル名は3文字以上です。");
        if (value.length() > 20) throw new IllegalArgumentException("サークル名は20文字以下です。");
    }
}
