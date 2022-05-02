package com.example.itddd.sns.domain.models.circle;

import com.example.itddd.sns.domain.models.user.User;

public interface CircleFactory {
    Circle create(CircleName name, User owner);
}
