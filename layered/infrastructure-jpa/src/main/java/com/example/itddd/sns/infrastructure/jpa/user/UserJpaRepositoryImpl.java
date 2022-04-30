package com.example.itddd.sns.infrastructure.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepositoryImpl extends JpaRepository<UserDataModel, String> {
}
