package com.example.itddd.sns.application.service.circle.common;

import com.example.itddd.sns.domain.models.circle.Circle;
import com.example.itddd.sns.domain.models.user.UserId;

import java.util.List;

public record CircleData(String id, String name, String ownerId, List<String> memberIdList) {
    public CircleData(Circle circle) {
        this(
                circle.getId().value(),
                circle.getName().value(),
                circle.getOwner().value(),
                circle.getMembers().stream().map(UserId::value).toList()
        );
    }
}
