package com.example.itddd.web.config;

import com.example.itddd.sns.application.service.user.UserApplicationService;
import com.example.itddd.sns.domain.models.user.UserFactory;
import com.example.itddd.sns.domain.models.user.UserRepository;
import com.example.itddd.sns.infrastructure.jpa.user.JpaUserRepository;
import com.example.itddd.sns.infrastructure.jpa.user.UserJpaRepositoryImpl;
import com.example.itddd.web.utility.domain.user.InMemoryUserFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.example.itddd.sns.infrastructure.jpa")
@EnableJpaRepositories("com.example.itddd.sns.infrastructure.jpa")
public class DebugConfig {
    @Bean
    UserApplicationService userApplicationService(UserFactory userFactory, UserRepository userRepository) {
        return new UserApplicationService(userFactory, userRepository);
    }

    @Bean
    UserFactory userFactory() {
        return new InMemoryUserFactory();
    }

    @Bean
    UserRepository userRepository(UserJpaRepositoryImpl jpaRepository) {
        return new JpaUserRepository(jpaRepository);
    }
}
