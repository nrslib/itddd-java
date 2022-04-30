package com.example.itddd.sns.domain.models.user;

import java.util.Objects;

public record UserName(String value) {
    public UserName {
        Objects.requireNonNull(value);
        if (value.length() < 3) throw new IllegalArgumentException("ユーザ名は3文字以上です。");
        if (value.length() > 20) throw new IllegalArgumentException("ユーザ名は20文字以下です。");
    }
}

// === class version. ===
//package com.example.itddd.sns.domain.models.user;
//
//import lombok.Getter;
//
//import java.util.Objects;
//
//public class UserName {
//    @Getter()
//    private final String value;
//
//    public UserName(String value) {
//        Objects.requireNonNull(value);
//        if (value.length() < 3) throw new IllegalArgumentException("ユーザ名は3文字以上です。");
//        if (value.length() > 20) throw new IllegalArgumentException("ユーザ名は20文字以下です。");
//
//        this.value = value;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserName userName = (UserName) o;
//        return Objects.equals(value, userName.value);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(value);
//    }
//}