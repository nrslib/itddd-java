package com.example.itddd.web.http.models.circles.post;

import org.springframework.util.StringUtils;

import java.util.Objects;

public record CirclePostRequestModel(String circleName, String ownerId) {
    public CirclePostRequestModel {
        if (!StringUtils.hasText(circleName)) {
            throw new IllegalArgumentException("circleName must not be empty.");
        }
        if (!StringUtils.hasText(ownerId)) {
            throw new IllegalArgumentException("ownerId must not be empty.");
        }
    }
}
