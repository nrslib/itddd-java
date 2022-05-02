package com.example.itddd.sns.domain.models.circle;

import java.util.List;
import java.util.Optional;

public interface CircleRepository {
    Optional<Circle> find(CircleId id);
    List<Circle> findAll();
    void save(Circle circle);
    void delete(CircleId id);
}
