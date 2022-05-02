package com.example.itddd.web.utility.domain.circle;

import com.example.itddd.sns.domain.models.circle.Circle;
import com.example.itddd.sns.domain.models.circle.CircleFactory;
import com.example.itddd.sns.domain.models.circle.CircleId;
import com.example.itddd.sns.domain.models.circle.CircleName;
import com.example.itddd.sns.domain.models.user.User;

import java.util.ArrayList;
import java.util.UUID;

public class InMemoryCircleFactory implements CircleFactory {
    @Override
    public Circle create(CircleName name, User owner) {
        var id = UUID.randomUUID().toString();
        var circleId = new CircleId(id);
        return new Circle(
                circleId,
                name,
                owner.getId(),
                new ArrayList<>()
        );
    }
}
