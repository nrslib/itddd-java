package com.example.itddd.sns.domain.models.circle;

public class CircleFullException extends RuntimeException {
    private CircleId id;

    public CircleFullException(CircleId id, String message) {
        super(message);

        this.id = id;
    }

    public CircleId getId() {
        return id;
    }
}
