package com.example.itddd.sns.domain.models.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> find(UserId id);
    List<User> findAll();
    void save(User user);
    void delete(UserId id);
}
