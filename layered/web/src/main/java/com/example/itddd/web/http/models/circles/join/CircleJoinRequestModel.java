package com.example.itddd.web.http.models.circles.join;

import org.springframework.util.StringUtils;

public record CircleJoinRequestModel(String memberId) {
    public CircleJoinRequestModel {
        if (!StringUtils.hasText(memberId)) {
            throw new IllegalArgumentException("memberId must not be empty.");
        }
    }
}
