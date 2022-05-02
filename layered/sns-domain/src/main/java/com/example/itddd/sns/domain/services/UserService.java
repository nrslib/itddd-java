package com.example.itddd.sns.domain.services;

import com.example.itddd.sns.domain.models.user.User;
import com.example.itddd.sns.domain.models.user.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean exists(User user) {
        var duplicatedUser = userRepository.find(user.getName());

        return duplicatedUser.isPresent();
    }
}
